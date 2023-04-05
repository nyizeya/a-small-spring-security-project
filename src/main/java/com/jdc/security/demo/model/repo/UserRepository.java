package com.jdc.security.demo.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.security.demo.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername (String username);
	
}
