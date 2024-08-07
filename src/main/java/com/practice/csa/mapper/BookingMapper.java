  package com.practice.csa.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Contract;
import com.practice.csa.responsedto.BookingResponse;
import com.practice.csa.responsedto.CarServiceResponse;

@Component
public class BookingMapper {

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private CarServiceMapper carServiceMapper;

	public BookingResponse mapToBookingResponse(Booking booking) {

		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setId(booking.getId());
		bookingResponse.setCarResponse(carMapper.mapToCarResponse(booking.getCar()));

		List<Contract> contracts = booking.getContracts();
		
		List<CarServiceResponse> list = contracts.stream().map(contract -> {

			CarService carService = contract.getCarService();
			CarServiceResponse carServiceResponse = carServiceMapper.mapToServiceResponse(carService);
			
			return carServiceResponse;

		}).toList();
		
		bookingResponse.setCarService(list);
		

		return bookingResponse;
	}

}
