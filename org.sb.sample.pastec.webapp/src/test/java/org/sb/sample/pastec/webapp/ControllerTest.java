package org.sb.sample.pastec.webapp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageReferentialService;
import org.sb.sample.image.client.IImageService;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.image.client.impl.Image;
import org.sb.sample.image.client.impl.ImageId;
import org.sb.sample.image.client.impl.ImageReferentialService;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.webapp.rest.ImageServiceMock;
import org.sb.sample.pastec.webapp.rest.PastecServiceMock;
import org.sb.sample.pastec.webapp.rest.Utils;

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
		Controller controller = new Controller(imageReferentialService, pastecService, null);
		controller.init();
		assertEquals(2, pastecService.inputstreams.size());
	}

	
	private void callCheckEmpty(IPastecService pastecService, IImageService imageService) throws IOException {
		Controller controller = new Controller(null /*not needed for this test */, pastecService, imageService);
//		PastecServiceRest pastecServiceRest = new PastecServiceRest(controller);
		ImageContentData contentData = controller.searchContentData(Utils.getMonaLisa());
		assertEquals("ImageContentData [lks=[]]", contentData.toString());
	}
	
	@Test
	public void testSearchContentDataImageNotFound() throws IOException {
		PastecServiceMock pastecService = new PastecServiceMock();
		pastecService.findImage = false;
		
		callCheckEmpty(pastecService, new ImageServiceMock());
	}

	@Test
	public void testSearchContentDataImageFoundDataNotFound() throws IOException {
		PastecServiceMock pastecService = new PastecServiceMock();
		pastecService.imageId = "2";
		
		callCheckEmpty(pastecService, new ImageServiceMock());
	}
	
	
	@Test
	public void testSearchContentDataImageFoundBadScore() throws IOException {
		PastecServiceMock pastecService = new PastecServiceMock();
		pastecService.score = 100;
		
		callCheckEmpty(pastecService, new ImageServiceMock());
	}

}
