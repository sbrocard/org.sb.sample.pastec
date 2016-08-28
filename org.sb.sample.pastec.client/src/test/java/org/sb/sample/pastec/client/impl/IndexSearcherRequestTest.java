package org.sb.sample.pastec.client.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.sb.sample.pastec.client.SearchResults;
import org.sb.sample.pastec.client.impl.IndexSearcherRequest;
import org.sb.sample.pastec.client.impl.PastecService;

public class IndexSearcherRequestTest {

	private File f= new File("/home/moi/Project/vagrant/vagrant-pastec/tests/mona-lisa.jpg");

	@Test
	public void testSearchIndexPost() {
		try {
			URI uri = new URI("http://localhost:4212/index/searcher");
			IndexSearcherRequest indexSearcher = new IndexSearcherRequest(uri);
			indexSearcher.searchIndexPost(f= new File("/home/moi/Project/vagrant/vagrant-pastec/tests/mona-lisa.jpg"));
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSearchIndexPostJson() {
		try {
			URI uri = new URI("http://localhost:4212/index/searcher");
			PastecService pastecService = new PastecService(uri);
		    SearchResults searchResults = pastecService.searchIndexPostJson(f);
		    System.out.println(searchResults);
		    assertEquals("SearchResults [boundingRects=[BoundingRect [x=38, y=97, height=447, widht=0], BoundingRect [x=38, y=97, height=447, widht=0]], imageIds=[1, 1234], scores=[821.0, 821.0], tags=[, ]]",  searchResults.toString());
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}

}
