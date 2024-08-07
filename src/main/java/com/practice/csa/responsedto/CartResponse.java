package com.practice.csa.responsedto;

import java.util.List;

public class CartResponse {

	private int cartId;
	private List<CarServiceResponse> carServiceResponse;
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<CarServiceResponse> getCarServiceResponse() {
		return carServiceResponse;
	}

	public void setCarServiceResponse(List<CarServiceResponse> carServiceResponse) {
		this.carServiceResponse = carServiceResponse;
	}

	
}
