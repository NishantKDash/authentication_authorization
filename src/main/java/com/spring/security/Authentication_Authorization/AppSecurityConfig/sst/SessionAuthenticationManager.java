package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring.security.Authentication_Authorization.users.UserEntity;
import com.spring.security.Authentication_Authorization.users.UserService;

public class SessionAuthenticationManager implements AuthenticationManager{

	
	private AuthTokenService authService;
	private UserService userService;
	
	public SessionAuthenticationManager(AuthTokenService authService , UserService userService)
	{
		this.authService = authService;
		this.userService = userService;
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication instanceof SessionAuthentication)
		{
			SessionAuthentication sessionAuthentication = (SessionAuthentication) authentication;
		    String sessionid = sessionAuthentication.getCredentials();
		    System.out.println(sessionid + "                   " + "kjdfkdjkdjfkdjf");
		    UserEntity user = authService.getUserFromToken(sessionid.toString());
		    //TODO : crypto failure on jwt verificaton
		    //TODO : check if jwt is expired
		 System.out.println("heree");
		    sessionAuthentication.setUser(user);
		    return sessionAuthentication;
		}
		
		return null;
	}

}
