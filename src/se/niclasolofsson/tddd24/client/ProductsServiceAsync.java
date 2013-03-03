package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Customer;
import se.niclasolofsson.tddd24.shared.Product;
import se.niclasolofsson.tddd24.shared.ShoppingCartEntry;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductsServiceAsync {
	void init(AsyncCallback<Void> callback);
	void getCategories(AsyncCallback<Category[]> categoriesCallback);
	void getProducts(AsyncCallback<Product[]> callback);
	
	void updateStock(Product p, AsyncCallback<Void> callback);
	void saveProduct(Product p, AsyncCallback<Void> callback);
	
	void saveOrder(Customer customer, ShoppingCartEntry[] entries, AsyncCallback<Void> callback);
	void saveCategory(Category c, AsyncCallback<Category> callback);
}
