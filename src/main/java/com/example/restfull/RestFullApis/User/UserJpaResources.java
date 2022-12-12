package com.example.restfull.RestFullApis.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restfull.RestFullApis.Jpa.PostRepository;
import com.example.restfull.RestFullApis.Jpa.UserRepository;
import com.example.restfull.RestFullApis.ResponseCodes.ResponseMessages;


import jakarta.validation.Valid;

@RestController
public class UserJpaResources {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJpaResources( UserRepository userRepository, PostRepository postRepository) {
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable int id) {
		Optional<User> userData = userRepository.findById(id);
		if(userData.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<User> entityUser = EntityModel.of(userData.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityUser.add(link.withRel("All-users"));
		
		return entityUser;
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public ResponseMessages deleteUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		else {
			userRepository.deleteById(id);
			return new ResponseMessages("User Deleted successfully");
		}
		
		
	}
	
	@PostMapping(path = "/jpa/users")
	public ResponseMessages addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		return new ResponseMessages("User Added successfully id : "+savedUser.getId());
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> getPostForUserById(@PathVariable int id) {
		Optional<User> userData = userRepository.findById(id);
		if(userData.isEmpty()) {
			throw new UserNotFoundException("id : "+id);
		}
		return userData.get().getPost();
		
		
		
	}
	
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> addPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id : "+id);
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
}
