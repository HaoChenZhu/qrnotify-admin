package com.nebrija.tfg.qrnotify.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.nebrija.tfg.qrnotify.admin"})
@EnableFeignClients
public class QrnotifyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrnotifyAdminApplication.class, args);
	}

}
