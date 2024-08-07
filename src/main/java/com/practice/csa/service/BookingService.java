package com.practice.csa.service;

import org.springframework.http.ResponseEntity;

import com.practice.csa.responsedto.BookingResponse;
import com.practice.csa.utility.ResponseStructure;

public interface BookingService {

	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(int carId);
}
