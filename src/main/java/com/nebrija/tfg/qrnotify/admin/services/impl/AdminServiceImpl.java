package com.nebrija.tfg.qrnotify.admin.services.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.Admin;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.AdminRepository;
import com.nebrija.tfg.qrnotify.admin.mappers.AdminMapper;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUpdateAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.services.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<ApiAdminResponseDto> getAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return adminMapper.fromEntitiesToDtos(admins);
    }

    @Override
    public ApiAdminResponseDto createAdmin(ApiAdminRequestDto admin) {
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
            admin.setPermissions(apiAdminRequestDto.getPermissions() !=null ? apiAdminRequestDto.getPermissions() : admin.getPermissions());
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
}
