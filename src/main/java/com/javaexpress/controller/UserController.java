package com.javaexpress.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.UserDto;
import com.javaexpress.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
//@CrossOrigins
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) // 201
	public UserDto createUser(@Valid @RequestBody UserDto userDto) {
		log.info("UserController :: createUser {}",userDto.getEmailAddress());
		return userService.save(userDto);
	}
	
	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable Integer userId) {
		log.info("UserController :: fetchUser for {} ",userId);
		return userService.findById(userId);
	}
	
	// TODO : DELETE,PUT - STUDENT Assingment
	
	@PutMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.CREATED) // 201
	public UserDto updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
		log.info("UserController :: updateUser {} {}", userId, userDto.getEmailAddress());
		return userService.update(userId, userDto);
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT) // 201
	public void deleteUser(@PathVariable Integer userId) {
		log.info("UserController :: deleteUser {}",userId);
		userService.delete(userId);
	}
	
	@GetMapping("/username/{username}")
	public UserDto getUserByUsername(@PathVariable String username) {
		log.info("UserController :: getUserByUsername {}",username);
		return userService.findByUsername(username);
	}
	
	@GetMapping
	public List<UserDto> getAllUsers() {
		log.info("UserController :: getAllUsers");
		return userService.findAll();
	}
}
