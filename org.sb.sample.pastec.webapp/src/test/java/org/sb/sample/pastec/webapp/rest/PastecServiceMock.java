package org.sb.sample.pastec.webapp.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.sb.sample.pastec.client.BoundingRect;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.SearchResults;

final class PastecServiceMock implements IPastecService {
	
	public boolean findImage = true;
	public String imageId = Utils.IMAGEID_1;
	public double score = 821.0;
	
	@Override
	public SearchResults searchIndexPostJson(InputStream input) throws IOException {
		return findImage ? createSearcheResult() : createEmptySearchResults();
	}

	@Override
	public SearchResults searchIndexPostJson(File f) throws IOException {
		return findImage ? createSearcheResult() : createEmptySearchResults();
	}

	private SearchResults createEmptySearchResults() {
		SearchResults searchResults = new SearchResults();
		searchResults.setScores(Collections.emptyList());
		searchResults.setBoundingRects(Collections.emptyList());
		searchResults.setImage_ids(Collections.emptyList());
		searchResults.setTags(Collections.emptyList());
		return searchResults;
	}
	
	private SearchResults createSearcheResult() {
		SearchResults searchResults = createEmptySearchResults();
		searchResults.setScores(Collections.singletonList(score));
		BoundingRect boundRect = new BoundingRect();
		boundRect.x = 38;
		boundRect.y = 97;
		boundRect.height = 447;
		boundRect.widht = 0;
		searchResults.setBoundingRects(Collections.singletonList(boundRect));
		searchResults.setImage_ids(Collections.singletonList(imageId));
		searchResults.setTags(Collections.emptyList());
		return searchResults;
	}
	

}