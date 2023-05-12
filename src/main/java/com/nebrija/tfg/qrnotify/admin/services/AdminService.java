package com.nebrija.tfg.qrnotify.admin.services;

import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiAdminResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiPermissionRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUpdateAdminRequestDto;
import com.nebrija.tfg.qrnotify.admin.models.ApiLoginResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    List<ApiAdminResponseDto> getAdmins();

    ApiLoginResponseDto login(String email, String password);

    ApiAdminResponseDto createAdmin(ApiAdminRequestDto admin);

    ApiAdminResponseDto updateAdmin(String identifier, ApiUpdateAdminRequestDto apiAdminRequestDto);

    ApiAdminResponseDto deleteAdmin(String identifier);

    ApiAdminResponseDto getAdmin(String identifier);

    ApiAdminResponseDto getAdminByEmail(String email);

    ApiAdminResponseDto addPermission(String identifier, List<ApiPermissionRequestDto> apiPermissionRequestDto);
}
