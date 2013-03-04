package se.niclasolofsson.tddd24.shared;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.Cookies;

public class ShoppingCart {
	HashMap<Integer, ShoppingCartEntry> entries = new HashMap<Integer, ShoppingCartEntry>();
	
//	public void createFromCookie() {
//		String cookie = Cookies.getCookie("cart");
//		
//		if(cookie != null) {
//			String[] entriesStr = cookie.split(" ");
//			
//			for (String entryStr : entriesStr) {
//				String[] temp = entryStr.split(",");
//			}
//		} else {
//			Cookies.setCookie("cart", "");
//		}
//	}
	
	public void addProduct(Product product, int amount) {
		if(! entries.containsKey(product.getId())) {
			entries.put(product.getId(), new ShoppingCartEntry(product, amount));
		} else {
			entries.get(product.getId()).increaseAmount();
		}
		product.reduceStock();
		Cookies.setCookie("cart", Cookies.getCookie("cart") + amount + "," + product.getId() + " ");
	}
	
	public IsWidget asWidget() {
		FluidRow r = new FluidRow();
		for(ShoppingCartEntry entry : entries.values()) {
			r.add(new Label(entry.getProduct().getName() + " - " + entry.getAmount() + " st"));
		}
		return r;
	}
	
	public double getTotalPrice() {
		double res = 0;
		
		for(ShoppingCartEntry entry : entries.values()) {
			res += entry.getPrice();
		}
		return res;
	}
	
	public ShoppingCartEntry[] getEntries() {
		return entries.values().toArray(new ShoppingCartEntry[entries.size()]);
	}

	public void clear() {
		entries.clear();
	}
}
