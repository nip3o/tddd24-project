package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Strong;
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
	
	public Order(Customer customer, ShoppingCartEntry[] entries, int orderId) {
		this(customer, entries);
		this.orderId = orderId;
	}
	
	public double getTotalPrice() {
		double res = 0;
		for (ShoppingCartEntry e : entries) {
			res += e.getPrice();
		}
		return res;
	}
	
	public IsWidget asWidget() {
		FluidRow r = new FluidRow();
		r.add(new Heading(5, "OrderID " + orderId));
		r.add(new FluidRow(customer.getName() + "<br>" + customer.getStreet() + "<br>" + customer.getPostalCode() + " " + customer.getCity()));
		
		for (ShoppingCartEntry e : entries) {
			r.add(new FluidRow(e.getAmount() + "x " + e.getProduct().getName() + " á " + e.getProduct().getPrice() + " SEK"));
		}
		r.add(new Strong("Total price: " + this.getTotalPrice() + " SEK"));
		return r;
	}
}
