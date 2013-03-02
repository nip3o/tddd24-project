package se.niclasolofsson.tddd24.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("webshop")
public interface ProductsService extends RemoteService {
	void init();
	Category[] getCategories();
}
