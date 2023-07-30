package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class SessionAuthenticationFilter extends AuthenticationFilter{
   
	public SessionAuthenticationFilter(SessionAuthenticationManager authenticationManager)
	{
		super(authenticationManager , new SessionAuthenticationConverter());
		
		this.setSuccessHandler((request , response , authentication)->{
			SecurityContextHolder.getContext().setAuthentication(authentication);
		});
	}
}
