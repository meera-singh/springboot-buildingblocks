package com.stack.simplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stack.simplify.restservices.entities.Order;
import com.stack.simplify.restservices.entities.User;
import com.stack.simplify.restservices.exceptions.UserNotFoundException;
import com.stack.simplify.restservices.repositories.OrderRepository;
import com.stack.simplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="users")
public class OrderController {
	 @Autowired
	 private UserRepository userRepository;
	 private OrderRepository orderRepository;
	 
	 //getAll orders for a user
	 @GetMapping("/{userid}/orders")
	 public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		 Optional<User> userOptional = userRepository.findById(userid);
		 if (!userOptional.isPresent())
			 throw new UserNotFoundException("User Not Found");
		 return userOptional.get().getOrders();
	 }
	 //Create Order
	 @PostMapping("{userid}/orders")
	 public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		 Optional<User> userOptional = userRepository.findById(userid);
		 if (!userOptional.isPresent())
			 throw new UserNotFoundException("User Not Found");
		 User user = userOptional.get();
		 order.setUser(user);
		 return orderRepository.save(order);
		 
	 }

}
