package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.ShoppingCart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {
	private ProductsServiceAsync productsService = GWT.create(ProductsService.class);
	private ShoppingCart cart;
	
	public void onModuleLoad() {
		 cart = new ShoppingCart();
		
		 RootPanel.get().add(new ProductList(productsService, cart));
		 productsService = GWT.create(ProductsService.class);
	}
}
