package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestdto.CarServiceRequest;
import com.practice.csa.responsedto.CarServiceResponse;
import com.practice.csa.utility.ResponseStructure;


public interface CarServiceService {

	public ResponseEntity<ResponseStructure<CarServiceResponse>> addService(CarServiceRequest carServiceRequest);
	
	public ResponseEntity<ResponseStructure<CarServiceResponse>> fetchByServiceId(int id);
	
	public ResponseEntity<ResponseStructure<List<CarServiceResponse>>> fetchAllServices();
	
	public ResponseEntity<ResponseStructure<CarServiceResponse>> deleteByServiceId(int id);
	
	public ResponseEntity<ResponseStructure<CarServiceResponse>> updateByServiceId(int id,CarServiceRequest carServiceRequest);
	
}
