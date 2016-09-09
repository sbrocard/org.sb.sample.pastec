package org.sb.sample.pastec.client.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.sb.sample.pastec.client.SearchResults;

/**
 * 
 * TODO sb, write a unit tests with a Mock for pastec server
 * TODO sb, move the existing test in a integration like test
 * @author sbrocard
 *
 */
public class AddImageRequestTest extends JerseyTest {

	private static final String ID_1 = "1";
	private static final String HTTP_LOCALHOST_4212 = "http://localhost:4212/";
	private static final String EXPECTED_RESULT = "ImageAdded [image_id=1, type=IMAGE_ADDED]";
	private static final String MONA_LISA_JPG = "mona-lisa.jpg";


	@Override
	protected Application configure() {
		return new ResourceConfig(PastecServiceMock.class);
	}
	
	/**
	 * Call the real pastec server
	 * It is not activated for unit testing as it needs a real pastec server running.
	 * This can be used for integration tests
	 */
//	@Test
	public void testAddImageWithRealPastecService() {
		try {
			URI uri = new URI(HTTP_LOCALHOST_4212); 
			callAddImageImpl(uri);
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAddImage() {
		try {
			URI uri = getBaseUri();
			callAddImageImpl(uri);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	private void callAddImageImpl(URI uri) throws IOException {
		InputStream inputstream = AddImageRequestTest.class.getResourceAsStream(MONA_LISA_JPG);

		PastecService pastecService = new PastecService(uri);
		pastecService.addImage(ID_1, inputstream).subscribe(imageAdded -> {
			assertEquals(EXPECTED_RESULT, imageAdded.toString());
			assertEquals(ID_1, imageAdded.image_id);
			assertEquals("IMAGE_ADDED", imageAdded.type);
		});
	}

}
