package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.ShoppingCart;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class MainController implements EntryPoint {
	private ProductsServiceAsync productsService = GWT.create(ProductsService.class);
	private ShoppingCart cart;
	private ProductList productList;
	private CheckoutView checkoutView;
	private AdminView adminView;
	
	public void onModuleLoad() {
		 cart = new ShoppingCart();
		 productList = new ProductList(productsService, cart, this);
		 checkoutView = new CheckoutView(productsService, cart, this);
		 adminView = new AdminView(productsService, this);
		
		 RootPanel.get().add(productList);
		 productsService = GWT.create(ProductsService.class);
	}
	
	public void showCheckout() {
		checkoutView.updateCart();
		
		RootPanel.get().clear();
		RootPanel.get().add(checkoutView);
	}

	public void showProductList() {
		productList.updateCart();
		
		RootPanel.get().clear();
		RootPanel.get().add(productList);
	}
	
	public void showAdmin() {
		RootPanel.get().clear();
		RootPanel.get().add(adminView);
	}
}
