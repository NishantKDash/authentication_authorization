package com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.security.Authentication_Authorization.users.UserService;

//@Component
public class JwtAuthenticationManager implements AuthenticationManager{
	
	private JwtService jwtService;
	private UserService userService;
	
	public JwtAuthenticationManager(JwtService jwtService , UserService userService)
	{
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 
		if(authentication instanceof JwtAuthentication)
		{
			JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
		    var jwtString = jwtAuthentication.getCredentials();
		    var username = jwtService.getUserNameFromJwt(jwtString);
		    //TODO : crypto failure on jwt verificaton
		    //TODO : check if jwt is expired
		    
		    var user = userService.findUserByUsername(username);
		    jwtAuthentication.setUser(user);
		    return jwtAuthentication;
		}
		
		return null;
	}
	
	

}
