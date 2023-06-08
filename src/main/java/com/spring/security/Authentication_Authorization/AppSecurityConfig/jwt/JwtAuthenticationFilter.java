package com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;

//@Component
public class JwtAuthenticationFilter extends AuthenticationFilter{
	
	public JwtAuthenticationFilter(JwtAuthenticationManager authenticationManager)
	{
		super(authenticationManager , new JwtAuthenticationConverter());
		
		this.setSuccessHandler((request , response , authentication)->{
			SecurityContextHolder.getContext().setAuthentication(authentication);
		});
	}

}
