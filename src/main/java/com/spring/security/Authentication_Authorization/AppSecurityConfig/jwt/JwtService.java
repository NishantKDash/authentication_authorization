package com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {
	
	public static final String SECRET = "kj3k4j334jkj4kj3hlk3jh43jksdfj8438783ksjfdk;ajfd";
	
	Algorithm algorithm = Algorithm.HMAC256(SECRET);
	
	public String createJwt(String username)
	{
		return JWT.create()
		    .withSubject(username)
		    .withIssuedAt(new Date())
		    .sign(algorithm);
		    
	}
	
	public String getUserNameFromJwt(String token)
	{
		return JWT.require(algorithm)
				.build()
				.verify(token)
				.getSubject();
	}

}
