package org.sb.sample.pastec.webapp.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.pastec.client.rest.ClientBuilderInstance;

/**
 * 
 * @author sbrocard
 *
 */
public class PastecServiceRestTest extends JerseyTest {

	private static final String INDEX_SEARCHER = "pastec/content";

	@Override
	protected Application configure() {
		return Utils.configure(this, PastecServiceRest.class);
	}
	
	@Test
	public void testSearchContentDataRestAccess() {
		try {
			URI uri = new URI(getBaseUri() + INDEX_SEARCHER);
			InputStream inputstream = Utils.getMonaLisa();
			ImageContentData contentData = searchContentDataImpl(uri, inputstream);
			
			assertEquals(1, contentData.lks.size());
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}
	
	private static <T> ImageContentData searchContentDataImpl(URI uri, T input) throws IOException {
		assertNotNull(input);
		final Client client = ClientBuilderInstance.INSTANCE.build();
		
	    final WebTarget target = client.target(uri);
	
	    ImageContentData searchResults = target.request(MediaType.APPLICATION_JSON_TYPE)
	    		.post(Entity.entity(input, MediaType.TEXT_PLAIN_TYPE), ImageContentData.class);
	    return searchResults;
	}
	
	public static void main(String[] args) {
		try {
			URI uri = new URI("http://localhost:8090/org.sb.sample.pastec.webapp/rest/" + INDEX_SEARCHER);
			InputStream inputstream = Utils.getMonaLisa();
			ImageContentData contentData = searchContentDataImpl(uri, inputstream);

			System.out.println(contentData);
//			assertEquals(1, contentData.lks.size());
		} catch (IOException | URISyntaxException e) {
			fail(e.getMessage());
		}
	}
}
