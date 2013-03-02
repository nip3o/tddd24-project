package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
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
	
	ProductsServiceAsync productsService;
	
	private static ProductListUiBinder uiBinder = GWT
			.create(ProductListUiBinder.class);

	interface ProductListUiBinder extends UiBinder<Widget, ProductList> {
	}

	private void listProducts(Product[] products) {
		FluidRow r;
		productList.clear();
	
		for(final Product p : products) {
			r = new FluidRow();
			r.add(new Label(p.getName()));
			productList.add(r);
		}
		
		if(products.length == 0) {
			r = new FluidRow();
			r.add(new Label("This category is empty!"));
			productList.add(r);
		}
	}
	
	private void listProducts(Category c) {
		final AsyncCallback<Product[]> productsCallback = new AsyncCallback<Product[]>() {
		      public void onFailure(Throwable caught) {}
		      public void onSuccess(Product[] result) {
		    	  listProducts(result);
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

	public ProductList(final ProductsServiceAsync productsService) {
		this.productsService = productsService;
		
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
