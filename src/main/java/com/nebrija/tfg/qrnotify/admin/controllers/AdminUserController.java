package com.nebrija.tfg.qrnotify.admin.controllers;

import com.nebrija.tfg.qrnotify.admin.model.api.*;
import com.nebrija.tfg.qrnotify.admin.services.AdminService;
import com.nebrija.tfg.qrnotify.admin.services.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "${chen.base_path}")
@Slf4j
public class AdminUserController implements AdminApi, UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AdminApi.super.getRequest();
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> deleteAdminById(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier) {
        ApiAdminResponseDto admin = adminService.deleteAdmin(identifier);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> getAdminById(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier) {
        ApiAdminResponseDto admin = adminService.getAdmin(identifier);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiAdminResponseDto>> getAdmins() {
        List<ApiAdminResponseDto> admins = adminService.getAdmins();
        return new ResponseEntity<>(admins,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> postAdmin(@ApiParam(value = "admin info"  )  @Valid @RequestBody(required = false) ApiAdminRequestDto apiAdminRequestDto) {
        ApiAdminResponseDto admin = adminService.createAdmin(apiAdminRequestDto);
        return new ResponseEntity<>(admin,HttpStatus.CREATED);
    }

        @Override
    public ResponseEntity<ApiAdminResponseDto> putAdmin(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier, @ApiParam(value = "admin info") @Valid @RequestBody(required = false) ApiUpdateAdminRequestDto apiUpdateAdminRequestDto) {
        ApiAdminResponseDto admin = adminService.updateAdmin(identifier, apiUpdateAdminRequestDto);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiUserResponseDto> getUserByPhone(String phone) {
        ApiUserResponseDto apiUserResponseDto=userService.getUserByPhoneNumber(phone);
        return new ResponseEntity<>(apiUserResponseDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiUserResponseDto>> getUsers() {
        return UserApi.super.getUsers();
    }

    @Override
    public ResponseEntity<ApiUserResponseDto> postUsers(ApiUserRequestDto apiUserRequestDto) {
        ApiUserResponseDto apiUserResponseDto=userService.createUser(apiUserRequestDto);
        return new ResponseEntity<>(apiUserResponseDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> verifyCode(@ApiParam(value = "User identifier",required=true) @PathVariable("phone") String phone,@ApiParam(value = "User auth code",required=true) @PathVariable("code") String code) {
        Boolean verifyCode=userService.verifyCode(phone,code);
        return new ResponseEntity<>(verifyCode,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> getAdminByEmail(@ApiParam(value = "User identifier",required=true) @PathVariable("email") String email) {
        ApiAdminResponseDto admin = adminService.getAdminByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


    }
