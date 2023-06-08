package com.spring.security.Authentication_Authorization.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.Authentication_Authorization.exceptions.InvalidInfo;
import com.spring.security.Authentication_Authorization.exceptions.UserAlreadyExists;
import com.spring.security.Authentication_Authorization.exceptions.UserNotFound;

@Service
public class UserService {
   
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	
	
	public UserEntity createUser(CreateUserDto userdto) throws Exception
	{
		if(userrepo.findByName(userdto.getName()) != null)
			throw new UserAlreadyExists();
		
		
		  UserEntity user = new UserEntity();
		  mapper.map(userdto, user);
		  user.setRole("USER");
		  user.setPassword(encoder.encode(user.getPassword()));
		  
		  
		  return userrepo.save(user);
		  
	}
	
	public UserEntity findUser(String name) throws Exception
	{
		if(userrepo.findByName(name) == null)
			throw new UserNotFound();
		
		return userrepo.findByName(name);
	}
	
	public UserResponseDto findUserByUsername(String name)
	{
		UserEntity user = userrepo.findByName(name);
		var response = mapper.map(user, UserResponseDto.class);
		return response;
	}
	
	public UserEntity verifyUser(LoginUserDto logindto) throws Exception
	{
		if(userrepo.findByName(logindto.getName()) == null)
			throw new UserNotFound();
		
		UserEntity foundUser = userrepo.findByName(logindto.getName());
		
		String op = foundUser.getPassword();
		String lp = logindto.getPassword();
		
		if(!encoder.matches(lp, op))
		{
			throw new InvalidInfo();
		}
			
		
		return foundUser;
	}
}
