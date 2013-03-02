package se.niclasolofsson.tddd24.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Main implements EntryPoint {
	public void onModuleLoad() {
		 RootPanel.get().add(new ProductList());
	}
}
