package com.nebrija.tfg.qrnotify.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nebrija.tfg.qrnotify.admin"})
public class QrnotifyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrnotifyAdminApplication.class, args);
	}

}
