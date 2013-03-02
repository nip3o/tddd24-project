package se.niclasolofsson.tddd24.client;

import java.io.Serializable;

public class NotFoundException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;

	public NotFoundException() {
	}

	public NotFoundException(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return this.name;
	}
}
