package com.spring.security.Authentication_Authorization;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.security.Authentication_Authorization.users.CreateUserDto;
import com.spring.security.Authentication_Authorization.users.UserController;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages ="com.spring.security.Authentication_Authorization")
public class AuthenticationAuthorizationApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(AuthenticationAuthorizationApplication.class, args);
		UserController usercontroller = context.getBean(UserController.class);
		usercontroller.createUser(new CreateUserDto("nishant" , "1234"));
		
	}

	
	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
