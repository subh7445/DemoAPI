package com.demo.api.controller;

import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.api.Service.ImageService;
import com.demo.api.Service.UserService;
import com.demo.api.entity.Image;
import com.demo.api.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ImageService imageService;
	
	  @PostMapping
	    public ResponseEntity<User> createUser(@RequestBody User user){
	        User savedUser = this.userService.createUser(user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }

	    // build get user by id REST API
	    // http://localhost:8080/api/users/1
	    @GetMapping("{id}")
	    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
	    	System.out.println("ok working!!");
	        User user = this.userService.getUserById(userId);
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<User>> getAllUsers(){
	        List<User> users = this.userService.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }

	    // Build Update User REST API
	    @PutMapping("{id}")
	    // http://localhost:8080/api/users/1
	    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
	                                           @RequestBody User user){
	        user.setId(userId);
	        User updatedUser = this.userService.updateUser(user);
	        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	    }

	    // Build Delete User REST API
	    @DeleteMapping("{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
	        this.userService.deleteUser(userId);
	        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	    }
	    
	    @PostMapping("/addImage")
	    public ResponseEntity<?> addImage(HttpServletRequest request, @RequestParam("image") MultipartFile file)
	    throws Exception{
	    	byte[] bytes = file.getBytes();
	    	Blob blob = new SerialBlob(bytes);
	    	Image image = new Image();
	    	
	    	image.setImage(blob);
	    	imageService.createImage(image);
	    	
	    	return  ResponseEntity.ok("Image successfully updated");
	    }
 
}
