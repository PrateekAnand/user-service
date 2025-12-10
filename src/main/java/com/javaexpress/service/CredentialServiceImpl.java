package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.mappers.ICredentialMapper;
import com.javaexpress.repository.ICredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialServiceImpl implements ICredentialService {

	@Autowired
	private ICredentialRepository credentialRepository;
	
	@Autowired
	private ICredentialMapper credentialMapper;
	
	@Override
	public CredentialDto findByUsername(String username) {
		log.info("CredentialServiceImpl :: findByUsername for {}", username);
		
		return credentialRepository.findByUsername(username)
				.map(credentialMapper::toDto)
				.orElseThrow(() -> new RuntimeException("Username not found in db"));
	}
	
	
}
