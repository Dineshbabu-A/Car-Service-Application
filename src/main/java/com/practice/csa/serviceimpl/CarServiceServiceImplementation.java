package com.practice.csa.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.CarService;
import com.practice.csa.exception.CarServiceIdNotFoundException;
import com.practice.csa.mapper.CarServiceMapper;
import com.practice.csa.repository.CarServiceRepository;
import com.practice.csa.requestdto.CarServiceRequest;
import com.practice.csa.responsedto.CarServiceResponse;
import com.practice.csa.service.CarServiceService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CarServiceServiceImplementation implements CarServiceService{
	
	@Autowired
	private CarServiceRepository carServiceRepository;
	
	@Autowired
	private CarServiceMapper carServiceMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponse>> addService(CarServiceRequest carServiceRequest) {
		
		CarService carService = carServiceRepository.save(carServiceMapper.mapToService(carServiceRequest));
		
		return	ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<CarServiceResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setData( carServiceMapper.mapToServiceResponse(carService))
				.setMessage("Car Service Object added"));
				
	}

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponse>> fetchByServiceId(int id) {
		
		return carServiceRepository.findById(id).map(carService -> ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<CarServiceResponse>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Car Service Object Found")
				.setData(carServiceMapper.mapToServiceResponse(carService))))
				.orElseThrow(()-> new CarServiceIdNotFoundException("Car Service Id Not Found"));
	}
	@Override
	public ResponseEntity<ResponseStructure<List<CarServiceResponse>>> fetchAllServices() {
		
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<CarServiceResponse>>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Car Service Objects")
				.setData(carServiceRepository.findAll()
						.stream()
						.map(service ->carServiceMapper.mapToServiceResponse(service))
						.toList()));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponse>> deleteByServiceId(int id) {
		
		return carServiceRepository.findById(id).map(carService ->{
			
			carServiceRepository.deleteById(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<CarServiceResponse>()
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("Car Service Object Deleted")
					.setData(carServiceMapper.mapToServiceResponse(carService)));
			
		}).orElseThrow(() -> new CarServiceIdNotFoundException("Car Service Id Not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CarServiceResponse>> updateByServiceId(int id, CarServiceRequest carServiceRequest) {
		
		return carServiceRepository.findById(id).map(excarService ->{
			
			CarService carService = carServiceMapper.mapToService(carServiceRequest);
			carService.setId(excarService.getId());
			
			carServiceRepository.save(carService);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<CarServiceResponse>()
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("Car Service Object Updated")
					.setData(carServiceMapper.mapToServiceResponse(carService)));
					
			
		}).orElseThrow(() -> new CarServiceIdNotFoundException("Car Service Object is Not Found"));
	}

}
