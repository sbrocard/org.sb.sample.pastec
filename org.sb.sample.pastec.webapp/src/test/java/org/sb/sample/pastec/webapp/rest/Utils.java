package org.sb.sample.pastec.webapp.rest;

import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.sb.sample.image.client.IImageService;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.image.client.Link;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.webapp.Controller;

public class Utils {

	public static final String IMAGEID_1 = "1";
	private static Binder binder;
	public static final String MONA_LISA_JPG = "mona-lisa.jpg";

	static {
		binder = new AbstractBinder() {
			// TODO sb, use JMock or something
	    	final IPastecService pastecService = new PastecServiceMock();
	    	final IImageService imageService = new ImageServiceMock();
	    	final Controller controller = new Controller(null, pastecService, imageService);
	    	
	        @Override
	        protected void configure() {
	            bind(imageService).to(IImageService.class);
	            bind(pastecService).to(IPastecService.class);
	            bind(controller).to(Controller.class);
	        }
	    };
	}
	
	private Utils() {
	}
	
    public static Application configure(JerseyTest injectMe, final Class<?>... classes) {
		ResourceConfig resourceConfig = new ResourceConfig(classes);
		resourceConfig.register(binder);

		ServiceLocator locator = ServiceLocatorUtilities.bind(binder);
		locator.inject(injectMe);

		return resourceConfig;
    }
    
	static ImageContentData createImageContentData() {
		ImageContentData contentData = new ImageContentData();
		contentData.lks = Collections.singletonList(new Link());
		return contentData;
	}
	
	public static InputStream getMonaLisa() {
		InputStream inputstream = Utils.class.getResourceAsStream(MONA_LISA_JPG);
		return inputstream;
	}
}
