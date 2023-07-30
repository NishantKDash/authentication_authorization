package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.spring.security.Authentication_Authorization.users.UserEntity;

public class SessionAuthentication implements Authentication{
	
	private String sessionid;
	private UserEntity user;
	
	public SessionAuthentication(String sessionid)
	{
		this.sessionid = sessionid;
	}
	public void setUser(UserEntity user)
	{
		this.user = user;
	}
	
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCredentials() {
		// TODO Auto-generated method stub
		return sessionid;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return user != null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
