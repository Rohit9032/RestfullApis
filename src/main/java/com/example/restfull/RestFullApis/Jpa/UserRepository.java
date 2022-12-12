package com.example.restfull.RestFullApis.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfull.RestFullApis.User.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}
