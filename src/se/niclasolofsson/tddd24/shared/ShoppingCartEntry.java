package se.niclasolofsson.tddd24.shared;

public class ShoppingCartEntry {
	private Product product;
	private int amount;

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
}
