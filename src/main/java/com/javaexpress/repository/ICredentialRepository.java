package com.javaexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Credential;

public interface ICredentialRepository extends JpaRepository<Credential, Integer> {

	// Method Names
	
		Optional<Credential> findByUsername(String username);
		
		Optional<Credential> findByusernameAndPassword(String username,String password);
		
		
		//@Query(value = "select uname,password from user",nativeQuery = true)
		//Object[] fetchUserINformation(String username);
}
