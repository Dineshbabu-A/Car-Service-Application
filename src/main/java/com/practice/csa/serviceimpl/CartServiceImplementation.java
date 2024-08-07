package com.practice.csa.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Cart;
import com.practice.csa.exception.CarServiceIdNotFoundException;
import com.practice.csa.exception.UserIdNotFoundException;
import com.practice.csa.mapper.CarServiceMapper;
import com.practice.csa.repository.CarServiceRepository;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responsedto.CarServiceResponse;
import com.practice.csa.responsedto.CartResponse;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CarServiceRepository carServiceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarServiceMapper carServiceMapper;


	public ResponseEntity<ResponseStructure<CartResponse>> addCarServiceToCart(int carServiceId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user ->{

			Cart cart = user.getCart();
			
			if(cart.getCarService() == null)
				cart.setCarService(new ArrayList());
			
			return carServiceRepository.findById(carServiceId).map(carService -> {

				
				cart.getCarService().add(carService);
				Cart updatedCart = cartRepository.save(cart);

				List<CarServiceResponse> services = updatedCart.getCarService()
						.stream()
						.map(carService2 -> carServiceMapper.mapToServiceResponse(carService2))
						.toList();
				
				CartResponse cartResponse=new CartResponse();
				cartResponse.setCartId(updatedCart.getId());
				cartResponse.setCarServiceResponse(services);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<CartResponse>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Cart Object Added").setData(cartResponse));
				

			}).orElseThrow(() -> new CarServiceIdNotFoundException("Car Service Is Not Found") );

		}).orElseThrow(() -> new UserIdNotFoundException("User Id Not Found Exception") );
	}


	@Override
	public ResponseEntity<ResponseStructure<CartResponse>> removeCarServiceFromCart(int carServiceId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user ->{

			Cart cart = user.getCart();
			
			return carServiceRepository.findById(carServiceId).map(carService ->{
				
				cart.getCarService().remove(carService);
				
				Cart updatedCart = cartRepository.save(cart);
				
				CartResponse cartResponse=new CartResponse();
				cartResponse.setCartId(updatedCart.getId());
				
				List<CarServiceResponse> services = updatedCart.getCarService()
						.stream()
						.map(carService2 -> carServiceMapper.mapToServiceResponse(carService2))
						.toList();
				cartResponse.setCarServiceResponse(services);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<CartResponse>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Cart Object Removed")
						.setData(cartResponse));
				
				
			}).orElseThrow( () -> new CarServiceIdNotFoundException("Car Service Id Not Found"));

		}).orElseThrow(() -> new UserIdNotFoundException("User Id Not Found Exception") );

	}


	@Override
	public ResponseEntity<ResponseStructure<CartResponse>> fetchAllCarServices() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		 return userRepository.findByEmail(email).map(user -> {
			
			Cart cart = user.getCart();
			
			List<CarServiceResponse> services = cart.getCarService().stream()
					.map(carService2 -> carServiceMapper.mapToServiceResponse(carService2))
					.toList();
			
			CartResponse cartResponse=new CartResponse();
			cartResponse.setCartId(cart.getId());
			cartResponse.setCarServiceResponse(services);
			  
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<CartResponse>()
					.setStatusCode(HttpStatus.CREATED.value())
					.setMessage("Cart Objects in Cart").setData(cartResponse));
			
		}).orElseThrow( () -> new UserIdNotFoundException("User Id Not Found Exception") );



	}
}
