package se.niclasolofsson.tddd24.shared;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	Category() {
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	private String name;
	private int id;
	
	public Category(String name, int id) {
		this.name = name;
		this.id = id;
	}	
}
