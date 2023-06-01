package com.nebrija.tfg.qrnotify.admin.services;

import com.nebrija.tfg.qrnotify.admin.model.api.ApiTokenResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiVerifyRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ApiUserResponseDto createUser(ApiUserRequestDto apiUserRequestDto);

    List<ApiUserResponseDto> getAllUsers();

    ApiUserResponseDto getUserByPhoneNumber(String phoneNumber);

    ApiTokenResponseDto verifyCode(ApiVerifyRequestDto apiVerifyRequestDto);

    ApiUserResponseDto getUserById(String id);
}
