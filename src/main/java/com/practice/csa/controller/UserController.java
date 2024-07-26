package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
		return userService.registerUser(userRequest);
	}

	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest){
		return userService.updateUser(userRequest);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(@PathVariable int id){
		return userService.deleteByUserId(id);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(@PathVariable int id){
		return userService.findByUserId(id);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		return userService.findAll();
	}
}
