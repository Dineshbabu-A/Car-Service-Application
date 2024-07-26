package com.practice.csa.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Car;
import com.practice.csa.exception.CarIdNotFoundException;
import com.practice.csa.mapper.CarMapper;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.requestdto.CarRequest;
import com.practice.csa.responsedto.CarResponse;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CarServiceImplementation implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarMapper carMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> addCar(CarRequest carRequest ) {
		Car car = carRepository.save(carMapper.mapToCar(carRequest));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<CarResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("Car Object Created")
				.setData(carMapper.mapToCarResponse(car)));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> fetchByCarId(int carId) {
		
		return  carRepository.findById(carId).map(car -> ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<CarResponse>()
						.setStatusCode(HttpStatus.FOUND.value())
						.setMessage("Car Object Found").setData(carMapper.mapToCarResponse(car))))
		.orElseThrow(() -> new CarIdNotFoundException("Car Id Not Found"));
			
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarResponse>>> fetchAll() {

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<CarResponse>>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Car Objects").setData(carRepository.findAll()
						.stream()
						.map(car -> carMapper.mapToCarResponse(car))
						.toList()));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> deleteById(int carId) {
		
	 return	carRepository.findById(carId).map(car -> {
			
			carRepository.deleteById(carId);
			
			 return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<CarResponse>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("Car Object Deleted").setData(carMapper.mapToCarResponse(car)));
	 }).orElseThrow(() -> new CarIdNotFoundException("Car Id Not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponse>> updateById(int carId, CarRequest carRequest) {
		
		return carRepository.findById(carId).map(exCar -> {
			
			 Car car = carMapper.mapToCar(carRequest);
			 car.setCarId(exCar.getCarId());
			 
			 carRepository.save(car);
			 
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<CarResponse>()
					 .setStatusCode(HttpStatus.OK.value())
					 .setMessage("Car Object Updated")
					 .setData(carMapper.mapToCarResponse(exCar)));
			 
		}).orElseThrow( () -> new CarIdNotFoundException("Car Id not Found"));
	}

	
}
