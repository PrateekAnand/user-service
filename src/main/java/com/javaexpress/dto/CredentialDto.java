package com.javaexpress.dto;

import com.javaexpress.models.RoleBasedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CredentialDto {
	
	
	private Integer credentialId;
	private String username;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;
}
