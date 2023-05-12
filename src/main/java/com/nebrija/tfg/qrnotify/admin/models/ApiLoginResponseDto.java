package com.nebrija.tfg.qrnotify.admin.models;

import lombok.Data;

@Data
public class ApiLoginResponseDto {
    private String accessToken;
    private String tokenType;
    private String idToken;
    private Integer expiresIn;
    private String scope;

}
