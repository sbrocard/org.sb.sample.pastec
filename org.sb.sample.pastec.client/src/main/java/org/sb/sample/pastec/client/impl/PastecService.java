package org.sb.sample.pastec.client.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.ImageAdded;
import org.sb.sample.pastec.client.SearchResults;
import org.sb.sample.pastec.client.rest.ClientBuilderInstance;

import rx.Observable;

public class PastecService implements IPastecService {

	private static final String INDEX_IMAGES = "index/images/";
	private URI uri;
	
	public PastecService(URI uri) {
		this.uri = uri;
	}

	@Override
	public SearchResults searchIndexPostJson(File f) throws IOException {
		IndexSearcherRequest indexSearcher = new IndexSearcherRequest(uri);
		return indexSearcher.searchIndexPostJson(f);
	}

	@Override
	public SearchResults searchIndexPostJson(InputStream inputstream) throws IOException {
		IndexSearcherRequest indexSearcher = new IndexSearcherRequest(uri);
		return indexSearcher.searchIndexPostJson(inputstream);
	}

	@Override
	public Observable<ImageAdded> addImage(String id, InputStream inputstream) {
		return Observable.create(o -> {
		final Client client = ClientBuilderInstance.INSTANCE.build();
		
	    final WebTarget target = client.target(uri);
	
	    ImageAdded imageAdded;
		try {
			imageAdded = target.path(INDEX_IMAGES).path(id).request(MediaType.APPLICATION_JSON_TYPE)
					.put(Entity.entity(inputstream, MediaType.TEXT_PLAIN_TYPE), ImageAdded.class);
		    o.onNext(imageAdded);
		    o.onCompleted();
		} catch (Exception e) {
			o.onError(e);
		}
		});
	}
}
