package com.stack.simplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stack.simplify.restservices.entities.User;
import com.stack.simplify.restservices.services.UserService;

@RestController
public class UserController {
//Autowire the userservice
	@Autowired
	private UserService userservice;
	@GetMapping ("/users")
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
	}
	//Create user method
	//Request body annotation
	//Post Mapping Annotation
	@PostMapping("/users")
	public User createUser(@RequestBody User user)
	{
		return userservice.createUser(user);
	}
	@GetMapping("/users/{id}")
	public Optional <User> getUserByID(@PathVariable("id") Long id){
		return userservice.getUserById(id);
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id, @RequestBody User user) {
		return userservice.updateUserById(id, user);
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 userservice.deleteUserById(id);
	}
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable ("username") String username) {
		return userservice.getUserByname(username);
	}
}

