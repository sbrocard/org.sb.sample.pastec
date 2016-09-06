package org.sb.sample.pastec.webapp;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.impl.PastecService;

public class MyApplication extends ResourceConfig {

	private PastecService pastecService;

	public MyApplication() {
		URI uri;
		try {
			uri = new URI("http://localhost:4212/index/searcher");
			pastecService = new PastecService(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registerFactories();
	}

	private void registerFactories() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(pastecService).to(IPastecService.class);
			}
		});
	}
}