package com.javaexpress.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	// dont load related data immeditately when main entity is loaded - load it only when we access
	// Credential entity owns the relatioship and the user field in it holds the forieng key
	//@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Credential credential;

}
