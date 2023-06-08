package com.spring.security.Authentication_Authorization.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
   
	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(Exception ex , WebRequest request) throws Exception
	{
		 ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		     return  new ResponseEntity<ErrorDetails>(errorDetails , HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex , WebRequest request) throws Exception
	{
		 ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		     return  new ResponseEntity<ErrorDetails>(errorDetails , HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(InvalidInfo.class)
	public ResponseEntity<ErrorDetails> handleInvalidInfoException(Exception ex , WebRequest request) throws Exception
	{
		 ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , ex.getMessage() , request.getDescription(false));
		     return  new ResponseEntity<ErrorDetails>(errorDetails , HttpStatus.FORBIDDEN);
	}
}
