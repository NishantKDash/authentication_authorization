package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class SessionAuthenticationConverter implements AuthenticationConverter{

	@Override
	public Authentication convert(HttpServletRequest request) {
		
		Cookie cookies [] = request.getCookies();
		
		
		if(cookies !=null)
		{
			
		for(Cookie cookie:cookies)
		{
			if(cookie.getName().equals("auth"))
			{
				String sessionid = (cookie.getValue());
				return new SessionAuthentication(sessionid);
			}
		}
		}
		return null;
	}

}
