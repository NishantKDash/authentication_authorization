package com.spring.security.Authentication_Authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserNotFound extends Exception{
   public UserNotFound()
   {
	   super("User does not exist");
   }
}
