package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String street;
	private String postalCode;
	private String city;
	
	Customer() {}
	
	public Customer(String name, String street, String postalCode, String city) {
		this.name = name;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}	
}
