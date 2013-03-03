package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AdminView extends Composite {
	@UiField
	Button addProduct;
	@UiField
	Button addCategory;
	
	@UiField
	Column orderList;
	@UiField
	TextBox categoryName;
	
	@UiField
	ListBox productCategory;
	@UiField
	TextBox productName;
	@UiField
	TextArea productDescription;
	@UiField
	TextBox productPrice;
	@UiField
	TextBox productStock;
	
	
	private ProductsServiceAsync productsService;
	private MainController controller;
	
	private static AdminViewUiBinder uiBinder = GWT
			.create(AdminViewUiBinder.class);

	interface AdminViewUiBinder extends UiBinder<Widget, AdminView> {
	}

	private void saveProduct() {
		final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Void result) {
		    	  productName.setValue("");
		    	  productDescription.setValue("");
		    	  productPrice.setValue("");
		    	  productStock.setValue("");
		      }
		};
		
		Product product = new Product(
				productName.getValue(), 
				productDescription.getValue(), 
				Float.parseFloat(productPrice.getValue()),
				Integer.parseInt(productStock.getValue()),
				Integer.parseInt(productCategory.getValue()));
		
		productsService.saveProduct(product, callback);
	}
	
	private void saveCategory() {
		final AsyncCallback<Category> callback = new AsyncCallback<Category>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Category result) {
		    	  categoryName.setValue("");
		    	  productCategory.addItem(result.getName(), result.getId()+ "");
		      }
		};
		Category c = new Category(categoryName.getValue());
		productsService.saveCategory(c, callback);
	}
	
	public AdminView(ProductsServiceAsync productsService, MainController mainController) {
		this.productsService = productsService;
		this.controller = mainController;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		final AsyncCallback<Category[]> callback = new AsyncCallback<Category[]>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Category[] result) {
		    	  for (Category category : result) {
		    		  productCategory.addItem(category.getName(), category.getId()+ "");
		    	  }
		    	  addProduct.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						saveProduct();
					}
		    	  });
		    	  addCategory.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						saveCategory();
					}
		    	  });
		      }
		};
		productsService.getCategories(callback);
	}

}
