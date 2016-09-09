package org.sb.sample.pastec.webapp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageReferentialService;
import org.sb.sample.image.client.impl.Image;
import org.sb.sample.image.client.impl.ImageId;
import org.sb.sample.image.client.impl.ImageReferentialService;
import org.sb.sample.pastec.webapp.rest.PastecServiceMock;

public class ControllerTest {

	@Test
	public void testInit() {
		List<IImage> images = new ArrayList<IImage>();
	
		{
			IImage image = new Image(new ImageId("1"), new File("/home/moi/git/org.sb.sample.pastec/org.sb.sample.pastec.webapp/src/test/resources/org/sb/sample/pastec/webapp/rest", "Jacques-Louis_David,_The_Coronation_of_Napoleon.jpg"));
			images.add(image);
		}
		{
			IImage image = new Image(new ImageId("2"), new File("/home/moi/git/org.sb.sample.pastec/org.sb.sample.pastec.webapp/src/test/resources/org/sb/sample/pastec/webapp/rest", "mona-lisa.jpg"));
			images.add(image);
		}
		
		IImageReferentialService imageReferentialService = new ImageReferentialService(images);
		PastecServiceMock pastecService = new PastecServiceMock();
		Controller controller = new Controller(imageReferentialService, pastecService);
		controller.init();
		assertEquals(2, pastecService.inputstreams.size());
	}

}
