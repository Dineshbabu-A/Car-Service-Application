package com.practice.csa.serviceimpl;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Cart;
import com.practice.csa.entity.User;
import com.practice.csa.enums.UserRole;
import com.practice.csa.exception.UserIdNotFoundException;
import com.practice.csa.exception.UserNotFoundException;
import com.practice.csa.mapper.UserMapper;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.requestdto.AuthRequest;
import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.security.JWTService;
import com.practice.csa.security.UserDetailImpl;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {

		User user = userMapper.mapToRequest(userRequest);
		user= userRepository.save(user);

		if(user.getUserRole().equals(UserRole.CUSTOMER)) {

			Cart cart=new Cart();
			cart.setUser(user);
			cartRepository.save(cart);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<UserResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("User Register Successfully")
				.setData(userMapper.mapToUserResponse(user)));


	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(exUser -> {

			User user = userMapper.mapToRequest(userRequest);
			user.setId(exUser.getId());

			userRepository.save(user);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<UserResponse>()
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("User Object Updated")
					.setData(userMapper.mapToUserResponse(user)));

		}).orElseThrow(()-> new UserIdNotFoundException("User Id Not Found Exception"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int id) {

		return userRepository.findById(id).map(user -> {

			userRepository.deleteById(user.getId());

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<UserResponse>()
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("User Object Deleted")
					.setData(userMapper.mapToUserResponse(user)));
		}).orElseThrow(() -> new UserIdNotFoundException("User Id Not Found")); 
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int id) {

		return userRepository.findById(id).map(user -> ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<UserResponse>()
						.setStatusCode(HttpStatus.FOUND.value())
						.setMessage("User Object Found")
						.setData(userMapper.mapToUserResponse(user))))
				.orElseThrow(() -> new UserIdNotFoundException("User Id Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll() {

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<UserResponse>>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("User Objects")
				.setData(userRepository.findAll()
						.stream()
						.map(user -> userMapper.mapToUserResponse(user))
						.toList()));
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> login(AuthRequest authRequest){
		
	
		String username = authRequest.getUsername();
		String password = authRequest.getPassword();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
		Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if(authenticate.isAuthenticated()) {

			SecurityContextHolder.getContext().setAuthentication(authenticate);
			
			return	userRepository.findByEmail(username).map(user -> {

				String jwt = jwtService.createJWT(user.getEmail(), user.getUserRole().name(), Duration.ofDays(1));

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<String>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("Login Successfull")
						.setData(jwt));
				
			}).orElseThrow(() -> new UserNotFoundException("User Not Found") );
		}else {
			throw new UserNotFoundException("User Not Found");
		}

	}



}
