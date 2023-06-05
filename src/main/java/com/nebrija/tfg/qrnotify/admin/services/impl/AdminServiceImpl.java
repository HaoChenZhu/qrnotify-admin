package com.nebrija.tfg.qrnotify.admin.services.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.AdminRepository;
import com.nebrija.tfg.qrnotify.admin.mappers.AdminMapper;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.services.AdminService;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.*;
import lombok.extern.slf4j.Slf4j;
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


}
