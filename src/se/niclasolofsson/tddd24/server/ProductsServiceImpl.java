package se.niclasolofsson.tddd24.server;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Customer;
import se.niclasolofsson.tddd24.shared.Product;
import se.niclasolofsson.tddd24.shared.ShoppingCartEntry;
import se.niclasolofsson.tddd24.client.ProductsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProductsServiceImpl extends RemoteServiceServlet implements ProductsService {
	private static final long serialVersionUID = 3L;
	
	private DataManager dm;
	
	@Override
	public void init() {
		dm = new DataManager();
		dm.connect();
//		dm.create();
		dm.close();
	}

	@Override
	public Category[] getCategories() {
		dm.connect();
		Category[] res = dm.getCategories();
		dm.close();
		return res;
	}

	@Override
	public Product[] getProducts() {
		dm.connect();
		Product[] res = dm.getProducts();
		dm.close();
		return res;
	}

	@Override
	public void saveProduct(Product p) {
		dm.connect();
		dm.saveProduct(p);
		dm.close();
	}

	@Override
	public void updateStock(Product p) {
		dm.connect();
		dm.updateStock(p);
		dm.close();
	}

	@Override
	public void saveOrder(Customer customer, ShoppingCartEntry[] entries) {
		dm.connect();
		dm.saveOrder(customer, entries);
		dm.close();
	}

	@Override
	public Category saveCategory(Category c) {
		dm.connect();
		Category res = dm.saveCategory(c);
		dm.close();
		return res;
	}


}
