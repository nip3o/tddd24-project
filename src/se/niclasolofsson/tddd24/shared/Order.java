package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.user.client.ui.IsWidget;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderId;
	private Customer customer;
	private ShoppingCartEntry[] entries;
	
	Order() {}
	
	public Order(Customer customer, ShoppingCartEntry[] entries) {
		this.customer = customer;
		this.entries = entries;
	}
	
	public IsWidget asWidget() {
		FluidRow r = new FluidRow();
		r.add(new Heading(5, orderId + ""));
		r.add(new FluidRow(customer.getName() + ", " + customer.getStreet() + "<br>" + customer.getCity() + " " + customer.getPostalCode() + " " + customer.getCity()));
		
		for (ShoppingCartEntry e : entries) {
			r.add(new FluidRow(e.getAmount() + " st" + e.getProduct().getName() + ", " + e.getPrice() + " SEK"));
		}
		return r;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
