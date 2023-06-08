package com.spring.security.Authentication_Authorization.users;

import lombok.Data;

@Data
public class UserResponseDto {
 
	  private Long id;
	  private String name;
	  private String token;
}
