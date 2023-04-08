package com.nebrija.tfg.qrnotify.admin.services;

import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ApiUserResponseDto createUser(ApiUserRequestDto apiUserRequestDto);

    List<ApiUserResponseDto> getAllUsers();

    ApiUserResponseDto getUserByPhoneNumber(String phoneNumber);

    boolean verifyCode(String phoneNumber, String code);


}
