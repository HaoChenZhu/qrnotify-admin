package com.nebrija.tfg.qrnotify.admin.services;

public interface SmService {
    void sendSms(String to,String verificationCode);
}
