package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int id);
	
	ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int id);
	
	ResponseEntity<ResponseStructure<List<UserResponse>>> findAll();
	
	
}
