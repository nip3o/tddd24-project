package se.niclasolofsson.tddd24.server;

import se.niclasolofsson.tddd24.shared.Category;
import se.niclasolofsson.tddd24.shared.Product;
import se.niclasolofsson.tddd24.client.ProductsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProductsServiceImpl extends RemoteServiceServlet implements ProductsService {
	private static final long serialVersionUID = 3L;
	
	private DataManager dm;
	
	@Override
	public void init() {
		dm = new DataManager();
		dm.connect();
		dm.create();
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
	public Product[] getProducts(Category c) {
		dm.connect();
		Product[] res = dm.getProducts(c);
		dm.close();
		return res;
	}

}
