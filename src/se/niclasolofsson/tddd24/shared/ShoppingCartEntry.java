package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class ShoppingCartEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product product;
	private int amount;

	ShoppingCartEntry() {}
	
	ShoppingCartEntry(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void increaseAmount() {
		amount++;
	}

	public double getPrice() {
		return amount * product.getPrice();
	}
}
