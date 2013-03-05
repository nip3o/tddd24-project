package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Customer;
import se.niclasolofsson.tddd24.shared.ShoppingCart;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CheckoutView extends Composite {
	@UiField
	Column shoppingCartList;
	@UiField
	Button backButton;
	@UiField
	Button saveButton;
	@UiField
	TextBox nameField;
	@UiField
	TextBox streetField;
	@UiField
	TextBox postalField;
	@UiField
	TextBox cityField;
	
	private MainController controller;
	private ShoppingCart cart;
	private ProductsServiceAsync productsService;
	
	private void saveOrder(Customer customer) {
		final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		    public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(Void result) {
				cart.clear();
				nameField.setValue("");
				streetField.setValue("");
				postalField.setValue("");
				cityField.setValue("");
				Window.alert("Your order has been saved!");
			}
		};
		productsService.saveOrder(customer, cart.getEntries(), callback);
	}

	
	private static CheckoutViewUiBinder uiBinder = GWT
			.create(CheckoutViewUiBinder.class);

	interface CheckoutViewUiBinder extends UiBinder<Widget, CheckoutView> {
	}

	public CheckoutView(final ProductsServiceAsync productsService, final ShoppingCart cart, MainController mainController) {
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
		
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Customer customer = new Customer(nameField.getValue(), streetField.getValue(), postalField.getValue(), cityField.getValue());
				saveOrder(customer);
			}
		});
	}
	
	public void updateCart() {
		shoppingCartList.clear();
		shoppingCartList.add(cart.asWidget());
		shoppingCartList.add(new FluidRow("Total " + cart.getTotalPrice() + " SEK"));
	}

}
