package com.Davidson.EncryptionSystem;

import com.Davidson.EncryptionSystem.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class EncryptionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptionSystemApplication.class, args);
	}

}
