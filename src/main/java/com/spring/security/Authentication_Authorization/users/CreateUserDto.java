package com.spring.security.Authentication_Authorization.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateUserDto {
    
	private String name;
	private String password;
	
}
