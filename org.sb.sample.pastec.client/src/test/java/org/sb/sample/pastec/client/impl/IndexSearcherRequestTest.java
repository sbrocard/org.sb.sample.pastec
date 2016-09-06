package org.sb.sample.pastec.client.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.sb.sample.pastec.client.SearchResults;

/**
 * 
 * TODO sb, write a unit tests with a Mock for pastec server
 * TODO sb, move the existing test in a integration like test
 * @author sbrocard
 *
 */
public class IndexSearcherRequestTest {

	/**
	 * TODO sb, do not use a local file
	 */
	private File f = new File("/home/moi/Project/vagrant/vagrant-pastec/tests/mona-lisa.jpg");

	@Test
	public void testSearchIndexPostJson() {
		try {
			URI uri = new URI("http://localhost:4212/index/searcher");
			PastecService pastecService = new PastecService(uri);
		    SearchResults searchResults = pastecService.searchIndexPostJson(f);
		    System.out.println(searchResults);
		    assertEquals("SearchResults [boundingRects=[BoundingRect [x=38, y=97, height=447, widht=0]], imageIds=[1], scores=[821.0], tags=[]]",  searchResults.toString());
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}

}
