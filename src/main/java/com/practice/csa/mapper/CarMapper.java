package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequest;
import com.practice.csa.responsedto.CarResponse;

@Component
public class CarMapper {

	public Car mapToCar(CarRequest carRequest) {
		
		Car car=new Car();
		car.setCarBrand(carRequest.getBrand());
		car.setCarModel(carRequest.getModel());
		
		return car;
	}
	
	public CarResponse mapToCarResponse(Car car) {
		
		CarResponse carResponse=new CarResponse();
		carResponse.setId(car.getCarId());
		carResponse.setBrand(car.getCarBrand());
		carResponse.setModel(car.getCarModel());
		
		return carResponse;
	}
}
