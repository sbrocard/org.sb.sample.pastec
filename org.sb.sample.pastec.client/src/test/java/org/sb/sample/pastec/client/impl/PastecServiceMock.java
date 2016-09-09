package org.sb.sample.pastec.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sb.sample.pastec.client.BoundingRect;
import org.sb.sample.pastec.client.SearchResults;

/**
 * Mock of the Pastec webservice
 * @author sbrocard
 *
 */
@Path("/index")
public class PastecServiceMock {

	public PastecServiceMock() {
	}

	@Path("/searcher")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public SearchResults sendMessage(InputStream uploadedInputStream) throws IOException {
		SearchResults searchResults = createSearcheResult();
		return searchResults;
	}

	@Path("/images/{id}")
	public ImageResourceMock sendMessage(@PathParam("id") String id) throws IOException {
		return new ImageResourceMock(id);
	}

	private SearchResults createSearcheResult() {
		SearchResults searchResults = new SearchResults();
		searchResults.setScores(Collections.singletonList(821.0));
		BoundingRect boundRect = new BoundingRect();
		boundRect.x = 38;
		boundRect.y = 97;
		boundRect.height = 447;
		boundRect.widht = 0;
		searchResults.setBoundingRects(Collections.singletonList(boundRect));
		searchResults.setImage_ids(Collections.singletonList("1"));
		searchResults.setTags(Collections.emptyList());
		return searchResults;
	}

} 