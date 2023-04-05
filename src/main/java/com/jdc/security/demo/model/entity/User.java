package com.jdc.security.demo.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private EncryptionAlgorithm algorithm;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> authorities;

	public enum EncryptionAlgorithm {
		BCRYPT, SCRYPT
	}

	public User(String username, String password, EncryptionAlgorithm algorithm, List<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.algorithm = algorithm;
		this.authorities = authorities;
	}

}
