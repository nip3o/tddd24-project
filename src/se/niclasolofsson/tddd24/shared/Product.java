package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Product() {}
	
	int id;
	int categoryId;
	String name;
	String description;
	float price;
	int stock;
	
	public IsWidget asWidget() {
		LabelType type = LabelType.SUCCESS;
		String stockText;
		FluidRow r = new FluidRow();
		
		Heading title = new Heading(3);
		title.setText(this.name);
		Label description = new Label(this.description);
		Label price = new Label(this.price + " SEK");
		
		if(this.stock > 0) {
			stockText = this.stock + " in stock";
			type = (this.stock == 1) ? LabelType.WARNING : LabelType.SUCCESS;

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
	
	public Product(int id, String name, String description, float price, int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public Product(String name, String description, float price, int stock,
			int categoryId) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.categoryId = categoryId;
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
	
	public float getPrice() {
		return this.price;
	}

	public int getCategoryId() {
		return this.categoryId;
	}
	
	public int getId() {
		return id;
	}
}
