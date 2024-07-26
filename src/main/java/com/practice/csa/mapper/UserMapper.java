package com.practice.csa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.User;
import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User mapToRequest(UserRequest userRequest) {
		
		String encode = passwordEncoder.encode(userRequest.getPassword());
		
		User user=new User();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(encode);
		user.setUserRole(userRequest.getUserRole());
		
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		
		UserResponse userResponse=new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		userResponse.setUserRole(user.getUserRole());
		
		return userResponse;
	}
}
