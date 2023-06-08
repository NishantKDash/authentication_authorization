package com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

//@Component
public class JwtAuthenticationConverter implements AuthenticationConverter{

	@Override
	public Authentication convert(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			var token = authHeader.substring(7);
			return new JwtAuthentication(token);
		}
		
		return null;
	}
	
	

}
