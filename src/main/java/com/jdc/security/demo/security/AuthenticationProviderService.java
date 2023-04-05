package com.jdc.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
		
		switch (user.getUser().getAlgorithm()) {
		case BCRYPT:
			return checkPassword(user, password, bCryptPasswordEncoder);
		
		case SCRYPT:
			return checkPassword(user, password, sCryptPasswordEncoder);
		}
		
		throw new BadCredentialsException("Chosen Encryption Algorithm Is InCorrect (BCrypt/SCrypt)");
	}
	
	private Authentication checkPassword(CustomUserDetails user, String password, PasswordEncoder passwordEncoder) {
		if (passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
					user.getUsername(), 
					user.getPassword(),
					user.getAuthorities());
		} 
		
		throw new BadCredentialsException("Passwords Don't Match");
	}

	@Override
	public boolean supports(Class<?> authClass) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authClass);
	}

}
