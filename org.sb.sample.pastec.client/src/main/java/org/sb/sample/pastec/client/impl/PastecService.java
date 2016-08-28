package org.sb.sample.pastec.client.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.SearchResults;

public class PastecService implements IPastecService {

	private URI uri;
	
	public PastecService(URI uri) {
		this.uri = uri;
	}

	@Override
	public SearchResults searchIndexPostJson(File f) throws IOException {
		IndexSearcherRequest indexSearcher = new IndexSearcherRequest(uri);
		return indexSearcher.searchIndexPostJson(f);
	}

}
