package com.nebrija.tfg.qrnotify.admin.services.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.User;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.UserRepository;
import com.nebrija.tfg.qrnotify.admin.exceptions.ApiRequestException;
import com.nebrija.tfg.qrnotify.admin.exceptions.ApiResourceNotFoundException;
import com.nebrija.tfg.qrnotify.admin.exceptions.ServerErrorException;
import com.nebrija.tfg.qrnotify.admin.mappers.UserMapper;

import com.nebrija.tfg.qrnotify.admin.model.api.ApiTokenResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserResponseDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiVerifyRequestDto;
import com.nebrija.tfg.qrnotify.admin.services.SmService;
import com.nebrija.tfg.qrnotify.admin.services.UserService;
import com.nebrija.tfg.qrnotify.admin.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SmService smService;

    private JwtUtil jwtUtil;

    @Override
    public ApiUserResponseDto createUser(ApiUserRequestDto apiUserRequestDto) {
        try {
            String code = generateCode();
            if(StringUtils.isBlank(apiUserRequestDto.getPhoneNumber()) || StringUtils.isBlank(apiUserRequestDto.getName())){
                throw new ApiRequestException("El nombre y el teléfono son obligatorios");
            }
            User user = userRepository.findByPhoneNumber(apiUserRequestDto.getPhoneNumber());
            if (user == null) {
                user = userMapper.toEntity(apiUserRequestDto);
            }
            user.setConfirmationCode(code);
            user.setName(apiUserRequestDto.getName());
            userRepository.save(user);
            smService.sendSms(apiUserRequestDto.getPhoneNumber(), code);
            return userMapper.toDto(user);
        } catch (Exception e) {
            throw new ServerErrorException("Error al crear el usuario");
        }
    }

    private String generateCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    @Override
    public List<ApiUserResponseDto> getAllUsers() {
        try {
            List<ApiUserResponseDto> users = userMapper.fromEntitiesToDtos(userRepository.findAll());
            if (users.isEmpty()) {
                throw new ApiResourceNotFoundException("No hay usuarios registrados");
            }
            return users;
        } catch (Exception e) {
            log.error("Error al obtener los usuarios", e);
            throw new ServerErrorException("Error al obtener los usuarios");
        }
    }

    @Override
    public ApiUserResponseDto getUserByPhoneNumber(String phoneNumber) {
        try{
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            return userMapper.toDto(user);
        }
        throw new ApiResourceNotFoundException("No existe el usuario con el teléfono " + phoneNumber);
        } catch (Exception e) {
            log.error("Error al obtener el usuario", e);
            throw new ServerErrorException("Error al obtener el usuario");
        }
    }

    @Override
    public ApiTokenResponseDto verifyCode(ApiVerifyRequestDto apiVerifyRequestDto) {
        try{
       User exists = userRepository.findByPhoneNumber(apiVerifyRequestDto.getPhoneNumber());
        if (exists != null && exists.getConfirmationCode().equals(apiVerifyRequestDto.getCode())) {
            ApiTokenResponseDto t = new ApiTokenResponseDto();
            t.setToken(jwtUtil.generateToken(apiVerifyRequestDto.getName(), apiVerifyRequestDto.getPhoneNumber()));
            return t;
        }
        throw new ApiResourceNotFoundException("No existe el usuario con el teléfono " + apiVerifyRequestDto.getPhoneNumber());
        } catch (Exception e) {
            log.error("Error al verificar el código", e);
            throw new ServerErrorException("Error al verificar el código");
        }
    }

    @Override
    public ApiUserResponseDto getUserById(String id) {
        try {
            User user = userRepository.findBy_id(id);
            if(user == null){
                throw new ApiResourceNotFoundException("No existe el usuario con el id " + id);
            }
            return userMapper.toDto(user);

        } catch (Exception e) {
            log.error("Error al obtener el usuario con id " + id, e);
            throw new ServerErrorException("Error al obtener el usuario");
        }
    }

}
