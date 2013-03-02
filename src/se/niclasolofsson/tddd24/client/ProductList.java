package se.niclasolofsson.tddd24.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProductList extends Composite {
	@UiField
	Column categories;
	
	private static ProductListUiBinder uiBinder = GWT
			.create(ProductListUiBinder.class);

	interface ProductListUiBinder extends UiBinder<Widget, ProductList> {
	}

	public ProductList() {
		initWidget(uiBinder.createAndBindUi(this));
		
		IconAnchor a;
		
		a = new IconAnchor();
		a.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent e) {
		    	Window.alert("Hello!");
		    }
		});
		a.setText("Giraffes");
		
		categories.add(a);
	}
}
