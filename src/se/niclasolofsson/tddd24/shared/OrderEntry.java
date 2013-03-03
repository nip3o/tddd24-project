package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class OrderEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	Customer customer;
	Product product;
	int amount;
	
	OrderEntry() {}
	
	public int getAmount() {
		return amount;
	}
	
	public int getProductId() {
		return product.getId();
	}
}
