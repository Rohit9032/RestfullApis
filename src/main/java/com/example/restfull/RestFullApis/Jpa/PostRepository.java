package com.example.restfull.RestFullApis.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfull.RestFullApis.User.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	

}
