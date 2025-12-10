package com.javaexpress.service;

import com.javaexpress.dto.CredentialDto;


public interface ICredentialService {

	CredentialDto findByUsername(String username);
}
// UserService userService = new UserServiceImpl();