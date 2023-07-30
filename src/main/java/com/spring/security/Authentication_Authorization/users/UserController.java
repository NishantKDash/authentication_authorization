package com.spring.security.Authentication_Authorization.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt.JwtService;
import com.spring.security.Authentication_Authorization.AppSecurityConfig.sst.AuthTokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserService userserv;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthTokenService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto userdto) throws Exception
	{
	       UserEntity createdUser = userserv.createUser(userdto);
	       UserResponseDto responseDto = new UserResponseDto();
	       mapper.map(createdUser, responseDto);
	       var token = jwtService.createJwt(responseDto.getName());
	       responseDto.setToken(token);
	       return  ResponseEntity.ok(responseDto);
	     
	}
	
	@GetMapping("/about")
	public ResponseEntity<String> about()
	{
		return ResponseEntity.ok("about");
	}
	
	@GetMapping("/user/{name}")
	//@PreAuthorize("#name == authentication.name")
	public ResponseEntity<UserResponseDto> retrieveUser(@PathVariable String name) throws Exception
	{
		   UserEntity user = userserv.findUser(name);
	       UserResponseDto responseDto = new UserResponseDto();
	       mapper.map(user, responseDto);
	       return  ResponseEntity.ok(responseDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> verifyUser(@RequestBody LoginUserDto logindto) throws Exception
	{
		UserEntity user = userserv.verifyUser(logindto);
		UserResponseDto responsedto = new UserResponseDto();
		mapper.map(user ,  responsedto);
		responsedto.setToken(jwtService.createJwt(responsedto.getName()));
		return ResponseEntity.ok(responsedto);
	}
	
	@PostMapping("/login_session_cookies")
	public ResponseEntity<UserResponseDto_v2> verifyUser(@RequestBody LoginUserDto logindto , HttpServletResponse response) throws Exception
	{
		UserEntity user = userserv.verifyUser(logindto);
		UserResponseDto_v2 responsedto = new UserResponseDto_v2();
		mapper.map(user ,  responsedto);
		Cookie cookie = new Cookie("auth" , authService.createToken(user).toString());
		response.addCookie(cookie);
		return ResponseEntity.ok(responsedto);
	}

}
