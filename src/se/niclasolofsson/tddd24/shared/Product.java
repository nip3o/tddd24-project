package se.niclasolofsson.tddd24.shared;

public class Product {
	String name;
	String description;
	float price;
	int stock;
	
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
