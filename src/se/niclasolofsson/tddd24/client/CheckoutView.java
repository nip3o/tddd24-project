package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.ShoppingCart;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CheckoutView extends Composite {
	@UiField
	Column shoppingCartList;
	@UiField
	Button backButton;
	@UiField
	Button saveButton;
	
	private MainController controller;
	private ShoppingCart cart;
	private ProductsServiceAsync productsService;
	
	
	private static CheckoutViewUiBinder uiBinder = GWT
			.create(CheckoutViewUiBinder.class);

	interface CheckoutViewUiBinder extends UiBinder<Widget, CheckoutView> {
	}

	public CheckoutView(ProductsServiceAsync productsService, ShoppingCart cart, MainController mainController) {
		this.controller = mainController;
		this.productsService = productsService;
		this.cart = cart;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		backButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				controller.showProductList();
			}
		});
	}
	
	public void updateCart() {
		shoppingCartList.clear();
		shoppingCartList.add(cart.asWidget());
		shoppingCartList.add(new FluidRow("Total " + cart.getTotalPrice() + " SEK"));
	}

}
