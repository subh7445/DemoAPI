package com.demo.api.Service;

import java.util.List;

import com.demo.api.entity.User;

public interface UserService {
     //to create user
	User createUser(User user);
	
	//to get user by ID
	User getUserById(Long userId);
	
	//now to get all user 
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
