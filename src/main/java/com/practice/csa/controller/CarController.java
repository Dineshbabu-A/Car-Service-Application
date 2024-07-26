package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequest;
import com.practice.csa.responsedto.CarResponse;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CarController {
	
	@Autowired
	 private CarService carService;
	
	@PostMapping ("/cars")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponse>> addCar (@RequestBody CarRequest carRequest){
		return carService.addCar(carRequest);
		
	}
	
	@GetMapping ("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponse>> fetchById(@PathVariable int carId){
		return carService.fetchByCarId(carId);
	}
	
	@GetMapping("/cars")
	public ResponseEntity<ResponseStructure<List<CarResponse>>> fetchAll(){
		return carService.fetchAll();
	}
	
	@DeleteMapping("/cars/{carId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponse>> deleteById(@PathVariable int carId){
		return carService.deleteById(carId);
	}
	
	@PutMapping("/cars/{carId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponse>> updateById(@PathVariable int carId, @RequestBody CarRequest carRequest){
		return carService.updateById(carId, carRequest);
	}

}
