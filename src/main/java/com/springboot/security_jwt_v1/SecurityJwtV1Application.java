package com.springboot.security_jwt_v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityJwtV1Application implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Encoded password 123:"+passwordEncoder.encode("123"));

	}
}
