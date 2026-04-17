package com.akash.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.entity.User;

public interface IUserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByEmailAndPwd(String email,String pwd);

}
