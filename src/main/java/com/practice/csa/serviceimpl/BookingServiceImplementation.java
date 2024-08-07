package com.practice.csa.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.Car;
import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Cart;
import com.practice.csa.entity.Contract;
import com.practice.csa.entity.User;
import com.practice.csa.exception.CarIdNotFoundException;
import com.practice.csa.exception.UserIdNotFoundException;
import com.practice.csa.mapper.BookingMapper;
import com.practice.csa.repository.BookingRepository;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.repository.ContractRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responsedto.BookingResponse;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class BookingServiceImplementation implements BookingService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingMapper bookingMapper;

	@Override
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(int carId) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User customer = userRepository.findByEmail(email).orElseThrow( () -> new UserIdNotFoundException("User Id Not Found "));
		
		Car car = carRepository.findById(carId).orElseThrow( () -> new CarIdNotFoundException("Car Id Not Found "));
		
		Booking booking=new Booking();
		booking.setCar(car);
		booking.setCustomer(customer);
		bookingRepository.save(booking);
		Cart cart = customer.getCart();
		List<CarService> carService = cart.getCarService();
		
		List<Contract> contracts=new ArrayList();
		
		for (CarService carService2 : carService) {
			
			Contract contract=new Contract();
			contract.setBooking(booking);
			contract.setCarService(carService2);
			contractRepository.save(contract);
			
			contracts.add(contract);
			
		}
		
		booking.setContracts(contracts);
		bookingRepository.save(booking);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<BookingResponse>()
				.setStatusCode(HttpStatus.CREATED.value()).setMessage("Booking Object Created")
				.setData(bookingMapper.mapToBookingResponse(booking)));
	}

}
