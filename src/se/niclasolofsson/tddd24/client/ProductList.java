package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;
import se.niclasolofsson.tddd24.shared.ShoppingCart;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProductList extends Composite {
	@UiField
	Column categoryList;
	@UiField
	Column productList;
	@UiField
	Column shoppingCartList;
	
	private ShoppingCart cart;
	
	ProductsServiceAsync productsService;
	
	private static ProductListUiBinder uiBinder = GWT
			.create(ProductListUiBinder.class);

	interface ProductListUiBinder extends UiBinder<Widget, ProductList> {
	}

	private void listProducts(final Category c, final Product[] products) {
		FluidRow r = new FluidRow();
		productList.clear();
		
		Heading category = new Heading(2, c.getName());
		r.add(category);
	
		for(final Product p : products) {
			r.add(p.asWidget());
			if(p.getStock() > 0) {
				Button addButton = new Button("Add to cart", IconType.PLUS);
				addButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						cart.addProduct(p, 1);
						shoppingCartList.clear();
						shoppingCartList.add(cart.asWidget());
						listProducts(c, products);
					}
				});
				r.add(addButton);	
			}
		}
		
		if(products.length == 0) {
			r.add(new Label("This category is empty!"));
		} 
		
		productList.add(r);
	}
	
	private void listProducts(final Category c) {
		final AsyncCallback<Product[]> productsCallback = new AsyncCallback<Product[]>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Product[] result) {
		    	  listProducts(c, result);
		      }
		};
		
		productsService.getProducts(c, productsCallback);
	}
	
	private void addCategories(Category[] categories) {
		FluidRow r;
		IconAnchor a;
		
		for(final Category c : categories) {
			r = new FluidRow();
			a = new IconAnchor();
			a.addClickHandler(new ClickHandler() {
			    public void onClick(ClickEvent e) {
			    	listProducts(c);
			    }
			});
			a.setText(c.getName());
			r.add(a);
			categoryList.add(r);
		}
	}

	public ProductList(final ProductsServiceAsync productsService, ShoppingCart cart) {
		this.productsService = productsService;
		this.cart = cart;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		final AsyncCallback<Category[]> categoriesCallback = new AsyncCallback<Category[]>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Category[] result) {
		    	  addCategories(result);
		      }
		};
		
		final AsyncCallback<Void> initCallback = new AsyncCallback<Void>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Void result) {
		    	  productsService.getCategories(categoriesCallback);
		      }
		};
		
		productsService.init(initCallback);
	}
}
