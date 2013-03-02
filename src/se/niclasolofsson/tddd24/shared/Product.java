package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	String description;
	float price;
	int stock;
	
	public Product() {
		
	}
	
	public IsWidget getWidget() {
		LabelType type = LabelType.SUCCESS;
		String stockText;
		FluidRow r = new FluidRow();
		
		Heading title = new Heading(3);
		title.setText(this.name);
		Label description = new Label(this.description);
		Label price = new Label(this.price + " SEK");
		
		if(this.stock > 0) {
			stockText = this.stock + " in stock";
			type = LabelType.SUCCESS;
			
		} else {
			stockText = "Not in stock";
			type = LabelType.IMPORTANT;
		}
		com.github.gwtbootstrap.client.ui.Label stock = new com.github.gwtbootstrap.client.ui.Label(type, stockText);

		r.add(title);
		r.add(description);
		r.add(price);
		r.add(stock);
		
		return r;
	}
	
	public Product(String name, String description, float price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStock() {
		return stock;
	}
	
	void reduceStock() {
		this.stock--;
	}
	
	float getPrice() {
		return this.price;
	}
}
