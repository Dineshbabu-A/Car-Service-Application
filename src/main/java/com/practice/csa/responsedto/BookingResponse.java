package com.practice.csa.responsedto;

import java.util.List;

import com.practice.csa.entity.CarService;

public class BookingResponse {

	private int id;

	private CarResponse carResponse;

	private List<CarServiceResponse> carService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarResponse getCarResponse() {
		return carResponse;
	}

	public void setCarResponse(CarResponse carResponse) {
		this.carResponse = carResponse;
	}

	public List<CarServiceResponse> getCarService() {
		return carService;
	}

	public void setCarService(List<CarServiceResponse> list) {
		this.carService = list;
	}



}
