package com.jdc.security.demo.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.security.demo.model.entity.User;
import com.jdc.security.demo.model.repo.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Supplier<UsernameNotFoundException> s = () -> {
			return new UsernameNotFoundException(String.format("User with name %s not found", username));
		};
		
		User user =  userRepository.findByUsername(username)
			.orElseThrow(s);
		
		return new CustomUserDetails(user);
	}

}
