package com.stack.simplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stack.simplify.restservices.entities.User;
import com.stack.simplify.restservices.exceptions.UserExistsException;
import com.stack.simplify.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		User existinguser = userRepository.findByUsername(user.getUsername());
		if (existinguser != null) {
			throw new UserExistsException ("User already exists");
		}
		return userRepository.save(user);
	}
	///get userby id
	
	public Optional<User> getUserById(long id) throws UserNotFoundException  {
		Optional <User> user=userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("User not found in use reository");
		}
		return user;
	}
	
	//Update by userid
	public User updateUserById(Long id,User user) throws UserNotFoundException
	{
		Optional <User> optionaluser=userRepository.findById(id);
		if(!optionaluser.isPresent()){
			throw new UserNotFoundException("User not found in user repository");}
		user.setId(id);
		return userRepository.save(user);
	}
	//delete userby id
	public void deleteUserById(Long id)  {
	Optional<User> optionaluser =	userRepository.findById(id);
		if (!optionaluser.isPresent()){
			throw new ResponseStatusException (HttpStatus.BAD_REQUEST,"User not found in user repository,provide the correct userid");
		}
		userRepository.deleteById(id);
	}
	//Get userbyname
	
	public User getUserByname(String username) {
		return userRepository.findByUsername(username);
	}
}
