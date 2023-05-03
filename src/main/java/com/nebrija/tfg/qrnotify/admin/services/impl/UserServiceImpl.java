package com.nebrija.tfg.qrnotify.admin.services.impl;

import com.nebrija.tfg.qrnotify.admin.dao.mongodb.entities.User;
import com.nebrija.tfg.qrnotify.admin.dao.mongodb.repository.UserRepository;
import com.nebrija.tfg.qrnotify.admin.mappers.UserMapper;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserRequestDto;
import com.nebrija.tfg.qrnotify.admin.model.api.ApiUserResponseDto;
import com.nebrija.tfg.qrnotify.admin.services.SmService;
import com.nebrija.tfg.qrnotify.admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SmService smService;

    @Override
    public ApiUserResponseDto createUser(ApiUserRequestDto apiUserRequestDto) {
        try {
            String code = generateCode();
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
            e.printStackTrace();
            return null;
        }
    }

    private String generateCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    @Override
    public List<ApiUserResponseDto> getAllUsers() {
        List<ApiUserResponseDto> users = userMapper.fromEntitiesToDtos(userRepository.findAll());
        return users;
    }

    @Override
    public ApiUserResponseDto getUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public boolean verifyCode(String phoneNumber, String code) {
       User exists = userRepository.findByPhoneNumber(phoneNumber);
        if (exists != null && exists.getConfirmationCode().equals(code)) {
            return true;
        }
        return false;
    }
}
