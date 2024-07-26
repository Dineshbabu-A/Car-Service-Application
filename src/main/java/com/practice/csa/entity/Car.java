package com.practice.csa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int carId;
	private String carBrand;
	private String carModel;


	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carBrand=" + carBrand + ", carModel=" + carModel + "]";
	}


}
