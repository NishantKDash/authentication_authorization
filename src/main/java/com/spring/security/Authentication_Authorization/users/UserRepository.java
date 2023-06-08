package com.spring.security.Authentication_Authorization.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
	public UserEntity findByName(String name);
}
