package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("products")
public interface ProductsService extends RemoteService {
	void init();
	Category[] getCategories();
	Product[] getProducts(Category c);
	void updateStock(Product p);
	void saveProduct(Product p);
}
