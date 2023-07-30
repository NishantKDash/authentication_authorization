package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.security.Authentication_Authorization.users.UserEntity;
import com.spring.security.Authentication_Authorization.users.UserRepository;

@Service
public class AuthTokenService {
     
	private AuthTokenRepository authtokenrepo;
	private UserRepository userrepo;
	
	
	public AuthTokenService(AuthTokenRepository authtokenrepo , UserRepository userrepo)
	{
		this.authtokenrepo = authtokenrepo;
		this.userrepo = userrepo;
	}
	
	
	public String createToken(UserEntity userEntity)
	{
		var token = new AuthTokenEntity();
		token.setUser(userEntity);
		authtokenrepo.save(token);
		return token.getToken().toString();
	}
	
	public UserEntity getUserFromToken(String token)
	{
		AuthTokenEntity authToken = authtokenrepo.findAuthEntityByToken(UUID.fromString(token));
		if(authToken == null)
			return null;
		return authToken.getUser();
	}
	
	public boolean isTokenValid(String token)
	{
		var authToken = authtokenrepo.findById(token).orElseThrow();
		if(LocalDate.now().isBefore(authToken.getCreatedAt()))
			return false;
		
		return true;

	}
}
