package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.service.CredentialServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/credentials")
@Slf4j
public class CredentialController {

	@Autowired
	private CredentialServiceImpl credentialService;
	
	@GetMapping("username/{uname}")
	public CredentialDto findByUsername(@PathVariable("uname") String username) {
		log.info("CredentialController :: findByUsername for {}", username);
		return credentialService.findByUsername(username);
	}
	
}
