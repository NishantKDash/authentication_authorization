package com.spring.security.Authentication_Authorization.AppSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt.JwtAuthenticationFilter;
import com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt.JwtAuthenticationManager;
import com.spring.security.Authentication_Authorization.AppSecurityConfig.jwt.JwtService;
import com.spring.security.Authentication_Authorization.users.UserService;

import jakarta.servlet.Filter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@ComponentScan(basePackages ="com.spring.security.Authentication_Authorization")
public class SecurityConfiguration {
  
	
	//CustomUserDetailsService customUserDetailsService;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	
//	public SecurityConfiguration(CustomUserDetailsService customUserDetailsService)
//	{
//		//this.customUserDetailsService = customUserDetailsService;
//	
//	}
	
	public SecurityConfiguration(JwtService jwtService , UserService userService)
	{
		jwtAuthenticationFilter = new JwtAuthenticationFilter(new JwtAuthenticationManager(jwtService , userService));
	}
	 @Bean
	 public SecurityFilterChain filter(HttpSecurity http) throws Exception
	 {
		 
		 http.csrf().disable();
			http.headers().frameOptions().disable(); //for enabling h2 console
			http.authorizeHttpRequests()
			.requestMatchers("/console/**").permitAll()
			.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.requestMatchers(HttpMethod.POST , "/register/**").permitAll()
			.requestMatchers(HttpMethod.POST , "/login").permitAll()
			
			.requestMatchers("/*/**")
			.authenticated()
			.and()
			.addFilterBefore((Filter)jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
			//http.httpBasic(Customizer.withDefaults());
			
			return http.build();
	 }

  

	
	 
		@Bean
		public WebMvcConfigurer corsConfigurer()
		{
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry)
				{
					registry.addMapping("/**")
					.allowedMethods("*")
					.allowedOriginPatterns("http://localhost:3000");
				}
			};
		}
		

}
