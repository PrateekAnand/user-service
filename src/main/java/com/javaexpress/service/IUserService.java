package com.javaexpress.service;

import java.util.List;

import com.javaexpress.dto.UserDto;
import com.javaexpress.models.User;

public interface IUserService {

	UserDto save(UserDto userDto);
	UserDto findById(Integer userId);
	UserDto update(Integer userId,UserDto user);
	UserDto findByUsername(String username);
	List<UserDto> findAll();
	void delete(Integer userId);
}
// UserService userService = new UserServiceImpl();