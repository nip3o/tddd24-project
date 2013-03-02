package se.niclasolofsson.tddd24.client;

public class Category {
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
