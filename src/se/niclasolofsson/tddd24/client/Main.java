package se.niclasolofsson.tddd24.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {
	private ProductsServiceAsync productsService = GWT.create(ProductsService.class);
	
	public void onModuleLoad() {
		 RootPanel.get().add(new ProductList(productsService));
		 
		productsService = GWT.create(ProductsService.class);

	}
}
