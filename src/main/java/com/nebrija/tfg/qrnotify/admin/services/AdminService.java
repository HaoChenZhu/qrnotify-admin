package com.nebrija.tfg.qrnotify.admin.services;

import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUpdateAdminRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    List<ApiAdminResponseDto> getAdmins();

    ApiAdminResponseDto createAdmin(ApiAdminRequestDto admin);

    ApiAdminResponseDto updateAdmin(String identifier, ApiUpdateAdminRequestDto apiAdminRequestDto);

    ApiAdminResponseDto deleteAdmin(String identifier);

    ApiAdminResponseDto getAdmin(String identifier);

    ApiAdminResponseDto getAdminByEmail(String email);
}
