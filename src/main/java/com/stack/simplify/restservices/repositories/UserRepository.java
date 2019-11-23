package com.stack.simplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stack.simplify.restservices.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);
		
}
