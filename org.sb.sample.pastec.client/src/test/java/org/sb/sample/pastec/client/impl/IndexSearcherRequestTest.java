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
public class IndexSearcherRequestTest extends JerseyTest {

	private static final String HTTP_LOCALHOST_4212 = "http://localhost:4212/";
	private static final String EXPECTED_RESULT = "SearchResults [boundingRects=[BoundingRect [x=38, y=97, height=447, widht=0]], imageIds=[1], scores=[821.0], tags=[]]";
	private static final String MONA_LISA_JPG = "mona-lisa.jpg";
	private static final String INDEX_SEARCHER = "index/searcher";

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
	public void testSearchIndexPostJsonWithRealPastecService() {
		try {
			URI uri = new URI(HTTP_LOCALHOST_4212 + INDEX_SEARCHER); 
			callIndexSearchImpl(uri);
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchIndexPostJson() {
		try {
			URI uri = new URI(getBaseUri() + INDEX_SEARCHER);
			callIndexSearchImpl(uri);
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	private void callIndexSearchImpl(URI uri) throws IOException {
		InputStream inputstream = IndexSearcherRequestTest.class.getResourceAsStream(MONA_LISA_JPG);

		PastecService pastecService = new PastecService(uri);
		SearchResults searchResults = pastecService.searchIndexPostJson(inputstream);
		assertEquals(EXPECTED_RESULT,  searchResults.toString());
	}

}
