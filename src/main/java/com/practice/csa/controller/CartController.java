package com.practice.csa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.responsedto.CartResponse;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/carServices/{carServiceId}/carts")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<ResponseStructure<CartResponse>> addCartService(@PathVariable int carServiceId){
		return cartService.addCarServiceToCart(carServiceId);
	}
	
	@DeleteMapping("/carServices/{carServiceId}/carts")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<ResponseStructure<CartResponse>> removeCarServiceFromCart(@PathVariable int carServiceId){
		return cartService.removeCarServiceFromCart(carServiceId);
	}
	
	@GetMapping("/carServices/carts")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<ResponseStructure<CartResponse>> fetchAllCarServices(){
		return cartService.fetchAllCarServices();
	}

}
