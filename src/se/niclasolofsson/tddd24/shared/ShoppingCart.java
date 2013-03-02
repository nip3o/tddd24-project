package se.niclasolofsson.tddd24.shared;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

public class ShoppingCart {
	HashMap<Product, ShoppingCartEntry> entries = new HashMap<Product, ShoppingCartEntry>();
	
	public void addProduct(Product product, int amount) {
		if(! entries.containsKey(product)) {
			entries.put(product, new ShoppingCartEntry(product, amount));
		} else {
			entries.get(product).increaseAmount();
		}
		product.reduceStock();
	}
	
	public IsWidget asWidget() {
		FluidRow r = new FluidRow();
		for(ShoppingCartEntry entry : entries.values()) {
			r.add(new Label(entry.getProduct().getName() + " - " + entry.getAmount() + " st"));
		}
		return r;
	}
}
