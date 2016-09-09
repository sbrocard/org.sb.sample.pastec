package org.sb.sample.pastec.webapp.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sb.sample.pastec.client.BoundingRect;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.ImageAdded;
import org.sb.sample.pastec.client.SearchResults;

import rx.Observable;

/**
 * This mock is called directly with sending a rest request.
 * It eases the other kinds of unit tests.
 * @author sbrocard
 *
 */
public final class PastecServiceMock implements IPastecService {

	public boolean findImage = true;
	public String imageId = Utils.IMAGEID_1;
	public double score = 821.0;

	public List<InputStream> inputstreams = new ArrayList<InputStream>();
	
	@Override
	public SearchResults searchIndexPostJson(InputStream inputstream) throws IOException {
		inputstreams.add(inputstream);
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

	@Override
	public Observable<ImageAdded> addImage(String id, InputStream inputstream) {
		return Observable.create(o -> {
			ImageAdded imageAdded = new ImageAdded();
			imageAdded.image_id = id;
			imageAdded.type = "IMAGE_ADDED";
			inputstreams.add(inputstream);

			o.onNext(imageAdded);
			o.onCompleted();
		});
	}


}