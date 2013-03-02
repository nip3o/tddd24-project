package se.niclasolofsson.tddd24.client;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductList extends Composite {
	@UiField
	Column categoryList;
	private ProductsServiceAsync productsService = GWT.create(ProductsServiceAsync.class);
	
	private static ProductListUiBinder uiBinder = GWT
			.create(ProductListUiBinder.class);

	interface ProductListUiBinder extends UiBinder<Widget, ProductList> {
	}
	
/*	private void addCategories(Category[] categories) {
		IconAnchor a;
		
		for(final Category c : categories) {
			a = new IconAnchor();
			a.addClickHandler(new ClickHandler() {
			    public void onClick(ClickEvent e) {
			    	Window.alert(c.getName() + " was the id");
			    }
			});
			a.setText(c.getName());
			categoryList.add(a);
		}
	}*/

	public ProductList() {
		initWidget(uiBinder.createAndBindUi(this));
		
/*		final AsyncCallback<Category[]> categoriesCallback = new AsyncCallback<Category[]>() {
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
		};*/
		
		//productsService = GWT.create(ProductsServiceAsync.class);
		//productsService.init(initCallback);
	}
}
