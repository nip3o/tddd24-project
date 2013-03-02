package se.niclasolofsson.tddd24.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;

public class DataManager {
	private Connection conn;
	
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:webshop.db");

		} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
		} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
		}
	}
	
	public void create() {
		Statement s;
		try {
			s = conn.createStatement();
			
			s.executeUpdate("DROP TABLE IF EXISTS categories");
			s.executeUpdate("CREATE TABLE categories (id INTEGER PRIMARY KEY, name STRING)");
			s.executeUpdate("INSERT INTO categories (name) VALUES ('Giraffes')");
			s.executeUpdate("INSERT INTO categories (name) VALUES ('Aligators')");
			
			s.executeUpdate("DROP TABLE IF EXISTS products");
			s.executeUpdate("CREATE TABLE products (id INTEGER PRIMARY KEY, name STRING, description TEXT, price FLOAT, stock INTEGER, category INTEGER)");
			s.executeUpdate("INSERT INTO products (name, description, price, stock, category) VALUES ('Big Aligator', 'A massive one!', 1255, 2, 2)");
			s.executeUpdate("INSERT INTO products (name, description, price, stock, category) VALUES ('Small Aligator', 'For the small home', 620, 4, 2)");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Category[] getCategories() {
		PreparedStatement s;
		ArrayList<Category> res = new ArrayList<Category>();
		
		try {
			s = conn.prepareStatement("SELECT * FROM categories");
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				res.add(new Category(rs.getString("name"), rs.getInt("id")));
			}
			
	        rs.close();
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res.toArray(new Category[res.size()]);
	}
	
	public Product[] getProducts(Category category) {
		PreparedStatement s;
		ArrayList<Product> res = new ArrayList<Product>();
		
		try {
			s = conn.prepareStatement("SELECT * FROM products WHERE category = ?");
			s.setInt(1, category.getId());
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				res.add(new Product(rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getInt("stock")));
			}
	        rs.close();
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res.toArray(new Product[res.size()]);
	}
	
	public void close() {
        try {
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
