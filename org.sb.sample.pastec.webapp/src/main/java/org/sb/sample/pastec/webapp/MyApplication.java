package org.sb.sample.pastec.webapp;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageReferentialService;
import org.sb.sample.image.client.IImageService;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.image.client.impl.Image;
import org.sb.sample.image.client.impl.ImageDataContentJsonReader;
import org.sb.sample.image.client.impl.ImageId;
import org.sb.sample.image.client.impl.ImageReferentialService;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.impl.PastecService;

public class MyApplication extends ResourceConfig {

	private final IPastecService pastecService;
	private final IImageReferentialService imageReferentialService;
	private final IImageService imageService = new IImageService() {
		
		@Override
		public ImageContentData findContentData(String imageId) {
			ImageContentData imageContentData = ImageDataContentJsonReader.readJson();

			return imageContentData;
		}
	};
	private Controller controller; 

	public MyApplication() {
		URI uri;
		IPastecService iPastecService = null;
		try {
			uri = new URI("http://localhost:4212/");
			iPastecService = new PastecService(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pastecService = iPastecService;
		List<IImage> images = new ArrayList<IImage>();
		
		{
			IImage image = new Image(new ImageId("1"), new File("/home/moi/git/org.sb.sample.pastec/org.sb.sample.pastec.webapp/src/test/resources/org/sb/sample/pastec/webapp/rest", "Jacques-Louis_David,_The_Coronation_of_Napoleon.jpg"));
			images.add(image);
		}
		{
			IImage image = new Image(new ImageId("2"), new File("/home/moi/git/org.sb.sample.pastec/org.sb.sample.pastec.webapp/src/test/resources/org/sb/sample/pastec/webapp/rest", "mona-lisa.jpg"));
			images.add(image);
		}
		imageReferentialService = new ImageReferentialService(images);
		controller = new Controller(imageReferentialService, pastecService, imageService);
		registerFactories();
		controller.init();
	}

	private void registerFactories() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(pastecService).to(IPastecService.class);
				bind(imageReferentialService).to(IImageReferentialService.class);
				bind(imageService).to(IImageService.class);
				bind(controller).to(Controller.class);
			}
		});
	}
}