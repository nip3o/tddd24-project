package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	String description;
	float price;
	int stock;
	
	public Product() {
		
	}
	
	public Product(String name, String description, float price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStock() {
		return stock;
	}
	
	void reduceStock() {
		this.stock--;
	}
	
	float getPrice() {
		return this.price;
	}
}
