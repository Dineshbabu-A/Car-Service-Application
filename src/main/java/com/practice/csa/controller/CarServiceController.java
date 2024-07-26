package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestdto.CarServiceRequest;
import com.practice.csa.responsedto.CarServiceResponse;
import com.practice.csa.service.CarServiceService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CarServiceController {

	@Autowired
	private CarServiceService service;
	
	@PostMapping("/carServices")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarServiceResponse>> addService(@RequestBody CarServiceRequest carServiceRequest){
		return service.addService(carServiceRequest);
	}
	
	@GetMapping("/carServices/{id}")
	public ResponseEntity<ResponseStructure<CarServiceResponse>> fetchByServiceId(@PathVariable int id){
		return service.fetchByServiceId(id);
	}
	
	@GetMapping("/carServices")
	public ResponseEntity<ResponseStructure<List<CarServiceResponse>>> fetchByService(){
		return service.fetchAllServices();
	}
	
	@DeleteMapping("/carServices/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarServiceResponse>> deleteByServiceId(@PathVariable int id){
		return service.deleteByServiceId(id);
	}
	
	@PutMapping("/carServices/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarServiceResponse>> updateByServiceId(@PathVariable int id,@RequestBody CarServiceRequest carServiceRequest){
		return service.updateByServiceId(id, carServiceRequest);
	}
	
}
