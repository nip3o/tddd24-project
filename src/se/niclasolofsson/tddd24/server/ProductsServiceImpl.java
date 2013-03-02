package se.niclasolofsson.tddd24.server;

import se.niclasolofsson.tddd24.shared.Category;
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
		Category[] res = (Category[]) dm.getCategories().toArray();
		dm.close();
		return res;
	}

}
