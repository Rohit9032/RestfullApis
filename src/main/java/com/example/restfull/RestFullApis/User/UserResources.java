package com.example.restfull.RestFullApis.User;

import java.net.URI;
import java.util.List;

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

import jakarta.validation.Valid;

@RestController
public class UserResources {
	
	private UserDao service;
	
	public UserResources(UserDao service) {
		this.service=service;
	}

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable int id) {
		User userData = service.getUserById(id);
		if(userData==null) {
			throw new UserNotFoundException("id : "+id);
		}
		EntityModel<User> entityUser = EntityModel.of(userData);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityUser.add(link.withRel("All-users"));
		
		return entityUser;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
		
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location ).build();
	}
}
