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



    ApiAdminResponseDto createAdmin(ApiAdminRequestDto admin);

}
