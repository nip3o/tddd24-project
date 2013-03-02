package se.niclasolofsson.tddd24.client;

import se.niclasolofsson.tddd24.shared.Category;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductsServiceAsync {
	void init(AsyncCallback<Void> callback);

	void getCategories(AsyncCallback<Category[]> categoriesCallback);
}
