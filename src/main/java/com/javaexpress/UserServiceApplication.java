package com.javaexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;


@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	/*
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/

	@Bean
	MicrometerCapability micrometerCapability(MeterRegistry registry) {
		return new MicrometerCapability(registry);
	}
}
