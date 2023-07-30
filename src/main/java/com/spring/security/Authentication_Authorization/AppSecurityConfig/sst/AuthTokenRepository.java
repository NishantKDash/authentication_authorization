package com.spring.security.Authentication_Authorization.AppSecurityConfig.sst;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.Authentication_Authorization.users.UserEntity;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity,String> {
   
	
	 AuthTokenEntity findAuthEntityByToken(UUID token);
}
