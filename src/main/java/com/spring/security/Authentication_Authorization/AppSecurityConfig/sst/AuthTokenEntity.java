package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.util.UUID;

import com.spring.security.Authentication_Authorization.users.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "auth-tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthTokenEntity {
  
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private UUID token;
	 
	 
	 @ManyToOne
	 private UserEntity user;
}
