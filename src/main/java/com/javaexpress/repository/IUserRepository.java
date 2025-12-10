package com.javaexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByCredentialUsername(String username);
}
