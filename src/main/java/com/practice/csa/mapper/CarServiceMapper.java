package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.CarService;
import com.practice.csa.requestdto.CarServiceRequest;
import com.practice.csa.responsedto.CarServiceResponse;

@Component
public class CarServiceMapper {

	public CarService mapToService(CarServiceRequest carServiceRequest) {
		
		CarService service=new CarService();
		service.setType(carServiceRequest.getType());
		service.setDescription(carServiceRequest.getDescription());
		service.setCost(carServiceRequest.getCost());
		
		return service;
	}
	
	public CarServiceResponse mapToServiceResponse(CarService service) {
		
		CarServiceResponse carServiceResponse=new CarServiceResponse();
		carServiceResponse.setId(service.getId());
		carServiceResponse.setType(service.getType());
		carServiceResponse.setDescription(service.getDescription());
		carServiceResponse.setCost(service.getCost());
		
		return carServiceResponse;
	}
}
