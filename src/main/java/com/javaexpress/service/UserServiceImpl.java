package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.UserNotFoundException;
import com.javaexpress.mappers.ICredentialMapper;
import com.javaexpress.mappers.IUserMapper;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserMapper userMapper;
	
	@Autowired
	private ICredentialMapper credentialMapper;
	
	@Autowired
	private ICredentialService credentialService;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto save(UserDto userDto) {
		log.info("UserServiceImpl :: save for {}", userDto.getEmailAddress());
		User user=userMapper.toEntity(userDto);
		Credential credential=user.getCredential();
		//TODO : we are providing original password in db but we need to store in encoded password
		/*
		String originalPassword = credential.getPassword();
		String encodedPassword = passwordEncoder.encode(originalPassword);
		credential.setPassword(encodedPassword);
		*/
		//BiDirectional
		credential.setUser(user);
		User dbUser=userRepository.save(user);
		
		return userMapper.toDto(dbUser);
	}

	@Override
	public UserDto findById(Integer userId) {
		log.info("UserServiceImpl :: findById for {}", userId);
		return userRepository.findById(userId).map(userMapper::toDto)
			   .orElseThrow(() -> new RuntimeException("User Not found in Db"));
	}

	@Override
	public UserDto update(Integer userId, UserDto userDto) {//should not modify the username because we are getting the credential id using that field
		log.info("UserServiceImpl :: update for {} {}", userId, userDto.getEmailAddress());
		
		findById(userId);
		
		User user=userMapper.toEntity(userDto);
		Credential credential=user.getCredential();
		Integer credentialId=credentialService.findByUsername(credential.getUsername()).getCredentialId();
		credential.setCredentialId(credentialId);
		credential.setUser(user);
		user.setUserId(userId);
		User dbUser= userRepository.save(user); // updating the record in db
		
		// entity to userdto
		UserDto result = userMapper.toDto(dbUser);
		result.setCredential(credentialMapper.toDto(credential));
		return result; 
	}
	
	@Override
	public void delete(Integer userId) {
		log.info("UserServiceImpl :: delete for {}", userId);

		findById(userId);
		
		userRepository.deleteById(userId);
	}
	
	@Override
	public UserDto findByUsername(String username) {
		log.info("UserService :: findByUsername({})", username);
		return userRepository.findByCredentialUsername(username).map(userMapper::toDto)
				.orElseThrow(() -> new UserNotFoundException("User with username '" + username + "' not found."));
	}
	
	@Override
	public List<UserDto> findAll() {
		log.info("UserService :: findAll");
		return userRepository.findAll().stream().sorted(Comparator.comparing(User::getUserId)).map(userMapper::toDto)
				.collect(Collectors.toList());
	}
	
	
}
