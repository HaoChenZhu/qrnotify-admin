package com.nebrija.tfg.qrnotify.admin.services.impl;

import com.nebrija.tfg.qrnotify.admin.clients.TopicClient;
import com.nebrija.tfg.qrnotify.admin.clients.impl.TopicClientImpl;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Permission;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.AdminRepository;
import com.nebrija.tfg.qrnotify.admin.mappers.AdminMapper;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiPermissionRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUpdateAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.models.ApiLoginResponseDto;
import com.nebrija.tfg.qrnotify.admin.services.AdminService;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private TopicClient topicClient;

    @Autowired
    private TopicClientImpl topicClientImpl;

    @Autowired
    private Client oktaClient;

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    @Value("${okta.oauth2.scope}")
    private String scope;

    @Override
    public List<ApiAdminResponseDto> getAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return adminMapper.fromEntitiesToDtos(admins);
    }

    @Override
    public ApiAdminResponseDto createAdmin(ApiAdminRequestDto admin) {
        User newUser = UserBuilder.instance().setProfileProperties(
                Map.of(
                        "email", admin.getEmail(),
                        "login", admin.getEmail(),
                        "firstName", admin.getName(),
                        "lastName", admin.getName()))
                .setPassword(admin.getPassword().toCharArray())
                .buildAndCreate(oktaClient);
          if (newUser == null) {
            try {
                throw new Exception("Error al registrar el usuario");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Activate the user
        newUser.activate(false);
        if (newUser.getStatus() != UserStatus.ACTIVE) {
            try {
                throw new Exception("Error al activar el usuario");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Admin adminEntity = adminMapper.toEntity(admin);
        adminRepository.save(adminEntity);
        return adminMapper.toDto(adminEntity);
    }


    @Override
    public ApiAdminResponseDto updateAdmin(String identifier, ApiUpdateAdminRequestDto apiAdminRequestDto) {
        Admin admin = adminRepository.findBy_id(identifier);
        if (admin != null) {
            admin.setName(StringUtils.isNotBlank(apiAdminRequestDto.getName()) ? apiAdminRequestDto.getName() : admin.getName());
            admin.setPassword(StringUtils.isNotBlank(apiAdminRequestDto.getPassword()) ? apiAdminRequestDto.getPassword() : admin.getPassword());
            adminRepository.save(admin);
            return adminMapper.toDto(admin);
        }
        return adminMapper.toDto(admin);
    }

    @Override
    public ApiAdminResponseDto deleteAdmin(String identifier) {
        Admin admin = adminRepository.findBy_id(identifier);
        if (admin != null) {
            adminRepository.delete(admin);
            return adminMapper.toDto(admin);
        }
        return null;
    }

    @Override
    public ApiAdminResponseDto getAdmin(String identifier) {
        Admin admin = adminRepository.findBy_id(identifier);
        return adminMapper.toDto(admin);
    }

    @Override
    public ApiAdminResponseDto getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        return adminMapper.toDto(admin);
    }

    @Override
    public ApiAdminResponseDto addPermission(String identifier, List<ApiPermissionRequestDto> apiPermissionRequestDto) {
        Admin admin = adminRepository.findBy_id(identifier);
        if (admin == null) {
            log.error("Admin with identifier {} not found", identifier);
            return null;
        }
        List<Permission> permissions = admin.getPermissions();

        for (ApiPermissionRequestDto permission : apiPermissionRequestDto) {
            if(permissions !=null) {
                boolean exists = permissions.stream()
                        .anyMatch(p -> p.getTopic_id().equals(permission.getTopicId()));
                if (exists) {
                    continue;
                }
            }else{
                permissions = new ArrayList<>();
            }
            boolean existsTopic = topicClientImpl.existTopic(permission.getTopicId());
            if (!existsTopic) {
                log.error("Topic with identifier {} not found", permission.getTopicId());
                continue;
            }
            Permission permissionEntity = adminMapper.toPermissionEntity(permission);
            permissionEntity.setActive(true);
            permissions.add(permissionEntity);
        }
        admin.setPermissions(permissions);
        adminRepository.save(admin);
        return adminMapper.toDto(admin);
    }

}
