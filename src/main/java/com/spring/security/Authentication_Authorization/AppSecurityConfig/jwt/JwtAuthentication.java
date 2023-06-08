package com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.security.Authentication_Authorization.users.UserResponseDto;


//@Component
public class JwtAuthentication implements Authentication{
	
	
	private String jwtString;
	private UserResponseDto user;
	
	public JwtAuthentication(String jwtString)
	{
		 this.jwtString = jwtString;
	}
	
	void setUser(UserResponseDto user)
	{
		this.user = user;
	}

	@Override
	public String getName() {
		
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO not needed for now unless we have different resources acccessible for different roles
		return null;
	}

	@Override
	public String getCredentials() {
		// TODO this is where we return the string/token that is used for authentication
		return jwtString;
	}

	@Override
	public Object getDetails() {
		// TODO not needed for now
		return null;
	}

	@Override
	public UserResponseDto getPrincipal() {
		// TODO this is where we return the user/client that is getting authenticated
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		 return user!=null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	
		
	}

}
