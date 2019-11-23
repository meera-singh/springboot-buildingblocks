package com.stack.simplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.simplify.restservices.entities.User;
import com.stack.simplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	//Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;
//getAllUsers Method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Create User method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	///get userby id
	
	public Optional<User> getUserById(long id) {
		Optional <User> user=userRepository.findById(id);
		return user;
	}
	
	//Update by userid
	public User updateUserById(Long id,User user) 
	{
		user.setId(id);
		return userRepository.save(user);
	}
	//delete userby id
	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent()){
			userRepository.deleteById(id);
		}
	}
	//Get userbyname
	
	public User getUserByname(String username) {
		return userRepository.findByUsername(username);
	}
}
