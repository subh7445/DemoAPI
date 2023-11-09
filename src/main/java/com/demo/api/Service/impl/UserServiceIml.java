package com.demo.api.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.Service.UserService;
import com.demo.api.entity.User;
import com.demo.api.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceIml implements UserService {
        
	@Autowired
	private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long userId) {
    	   Optional<User> optionalUser = this.userRepository.findById(userId);
    	   return optionalUser.get();
    }
    
    
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    
    @Override
    public User updateUser(User user) {
    	
    	User ExistingUser = this.userRepository.findById(user.getId()).get();
    	ExistingUser.setName(user.getName());
    	ExistingUser.setAge(user.getAge());
    	ExistingUser.setDepartment(user.getDepartment());
    	ExistingUser.setEmail(user.getEmail());
    	return this.userRepository.save(ExistingUser);
    	
    }
    
    @Override
    public void deleteUser(Long userId) {
    	this.userRepository.deleteById(userId);
    }
}
