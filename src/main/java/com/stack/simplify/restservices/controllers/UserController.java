package com.stack.simplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stack.simplify.restservices.entities.User;
import com.stack.simplify.restservices.exceptions.UserExistsException;
import com.stack.simplify.restservices.exceptions.UserNameNotFoundException;
import com.stack.simplify.restservices.exceptions.UserNotFoundException;
import com.stack.simplify.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {
//Autowire the userservice
	@Autowired
	private UserService userservice;
	@GetMapping 
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
	}
	//Create user method
	//Request body annotation
	//Post Mapping Annotation
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder)
	{
		try {
			 userservice.createUser(user);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException (HttpStatus.BAD_REQUEST, ex.getMessage());
			
		}
	}
	//get UserByid
	@GetMapping("/{id}")
	public Optional <User> getUserByID(@PathVariable("id") Long id){
		try {
		return userservice.getUserById(id);} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	//Update userby id
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id")Long id, @RequestBody User user) {
		try{
			return userservice.updateUserById(id, user);}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage()); }
		}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 userservice.deleteUserById(id);
	}
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable ("username") String username) throws UserNameNotFoundException {
		User user= userservice.getUserByname(username);
		if(user==null) 
			throw new UserNameNotFoundException("Username:" + username + "not found in User repository");
		return user;
	}
}

