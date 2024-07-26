package com.practice.csa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.csa.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(username).map(user -> new UserDetailImpl(user))
				.orElseThrow(() -> new UsernameNotFoundException("User Email Not Found"));
	}

}
