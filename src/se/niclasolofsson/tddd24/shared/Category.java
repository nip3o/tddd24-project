package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int id;
	private Product[] products;
	
	Category() {
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Category(String name) {
		this.name = name;
	}
	
	public Category(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void addProducts(Product[] products) {
		this.products = products;
	}
	
	public Product[] getProducts() {
		return products;
	}
}
