package se.niclasolofsson.tddd24.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.client.NotFoundException;
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
			s.executeUpdate("CREATE TABLE categories (id INT NOT NULL AUTOINCREMENT, name STRING)");
			s.executeUpdate("INSERT INTO categories (name) VALUES ('Giraffes')");
			s.executeUpdate("INSERT INTO categories (name) VALUES ('Aligators')");
			
			s.executeUpdate("DROP TABLE IF EXISTS products");
			s.executeUpdate("CREATE TABLE products (id INT NOT NULL AUTOINCREMENT, name STRING, description TEXT, price FLOAT, stock INT, category INT)");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Category> getCategories() {
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
		
		return res;
	}
	
	public Product getProduct(String name) throws NotFoundException {
		PreparedStatement s;
		Product res = null;
		
		try {
			s = conn.prepareStatement("SELECT * FROM products WHERE name = ?");
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) {
				res = new Product(name, rs.getString("description"), rs.getFloat("price"), rs.getInt("stock"));
			} else {
				throw new NotFoundException(name);
			}
			
	        rs.close();
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
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
