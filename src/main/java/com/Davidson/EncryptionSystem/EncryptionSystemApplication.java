package com.Davidson.EncryptionSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class EncryptionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptionSystemApplication.class, args);

		System.out.println(LocalDateTime.now());
	}

}
