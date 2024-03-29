package com.nebrija.tfg.qrnotify.admin.controllers;

import com.nebrija.tfg.qrnotify.admin.exceptions.ApiRequestException;
import com.nebrija.tfg.qrnotify.admin.exceptions.NotImplementedException;
import com.nebrija.tfg.qrnotify.admin.model.api.*;
import com.nebrija.tfg.qrnotify.admin.services.AdminService;
import com.nebrija.tfg.qrnotify.admin.services.UserService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import javax.validation.Valid;
import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping(value = "${chen.base_path}")
@Slf4j
public class AdminUserController implements AdminApi, UserApi, LoginApi,VerifyApi{

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AdminApi.super.getRequest();
    }


    @GetMapping("/test")
    public ResponseEntity test() {
        throw new ApiRequestException("Test Exception");
    }
    @Override
    public ResponseEntity<ApiTokenResponseDto> verifyCode(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ApiVerifyRequestDto apiVerifyRequestDto) {
        ApiTokenResponseDto token = userService.verifyCode(apiVerifyRequestDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ApiUserResponseDto> login(@ApiParam(value = "user info"  )  @Valid @RequestBody(required = false) ApiUserRequestDto apiUserRequestDto) {
        ApiUserResponseDto apiUserResponseDto = userService.createUser(apiUserRequestDto);
        return new ResponseEntity<>(apiUserResponseDto, HttpStatus.OK);
    }

        @Override
    public ResponseEntity<ApiAdminResponseDto> postAdminPermission(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier, @ApiParam(value = "permission info") @Valid @RequestBody(required = false) List<ApiPermissionRequestDto> apiPermissionRequestDto) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> deleteAdminById(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier) {
        throw new NotImplementedException("Not implemented");

    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> getAdminById(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier) {
        throw new NotImplementedException("Not implemented");

    }

    @Override
    public ResponseEntity<List<ApiAdminResponseDto>> getAdmins() {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> postAdmin(@ApiParam(value = "admin info") @Valid @RequestBody(required = false) ApiAdminRequestDto apiAdminRequestDto) {
        ApiAdminResponseDto admin = adminService.createAdmin(apiAdminRequestDto);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiAdminResponseDto> putAdmin(@ApiParam(value = "User identifier", required = true) @PathVariable("identifier") String identifier, @ApiParam(value = "admin info") @Valid @RequestBody(required = false) ApiUpdateAdminRequestDto apiUpdateAdminRequestDto) {
        throw new NotImplementedException("Not implemented");

    }

    @Override
    public ResponseEntity<ApiUserResponseDto> getUserByPhone(@ApiParam(value = "User phone",required=true) @PathVariable("phone") String phone) {
        ApiUserResponseDto apiUserResponseDto = userService.getUserByPhoneNumber(phone);
        return new ResponseEntity<>(apiUserResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiUserResponseDto>> getUsers() {
        List<ApiUserResponseDto> apiUserResponseDto = userService.getAllUsers();
        return new ResponseEntity<>(apiUserResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiUserResponseDto> getUserById(@ApiParam(value = "User identifier",required=true) @PathVariable("identifier") String identifier) {
        ApiUserResponseDto apiUserResponseDto = userService.getUserById(identifier);
        return new ResponseEntity<>(apiUserResponseDto, HttpStatus.OK);
    }

        @Override
    public ResponseEntity<ApiUserResponseDto> postUsers(ApiUserRequestDto apiUserRequestDto) {
        ApiUserResponseDto apiUserResponseDto = userService.createUser(apiUserRequestDto);
        return new ResponseEntity<>(apiUserResponseDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiAdminResponseDto> getAdminByEmail(@ApiParam(value = "User identifier", required = true) @PathVariable("email") String email) {
        throw new NotImplementedException("Not implemented");

    }

}
