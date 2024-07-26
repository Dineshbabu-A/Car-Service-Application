package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequest;
import com.practice.csa.responsedto.CarResponse;
import com.practice.csa.utility.ResponseStructure;

public interface CarService {

	public ResponseEntity<ResponseStructure<CarResponse>> addCar(CarRequest carRequest);
	
	public ResponseEntity<ResponseStructure<CarResponse>> fetchByCarId(int carId);
	
	public ResponseEntity<ResponseStructure<List<CarResponse>>> fetchAll();
	
	public ResponseEntity<ResponseStructure<CarResponse>> deleteById(int carId);
	
	public ResponseEntity<ResponseStructure<CarResponse>> updateById(int carId,CarRequest carRequest);
}
