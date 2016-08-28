package org.sb.sample.pastec.client.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sb.sample.pastec.client.SearchResults;
import org.sb.sample.pastec.client.rest.ClientBuilderInstance;


public class IndexSearcherRequest {

	
	private final URI _uri;

	public IndexSearcherRequest(URI uri) {
		_uri = uri;
	}

	/**
	 * Search request
	 *
	 * This call performs a search in the index thanks to a request image. 
	 * It returns the id of the matched images from the most to the least relevant ones.
	 * <code>{</br>
     * 	"bounding_rects": [</br>
     * 		{"height":447,"width":318,"x":38,"y":97},</br>
     * 		{"height":447,"width":318,"x":38,"y":97}</br>
     * 	],</br>
     * 	</br>
     * 	"image_ids": [1,1234],</br>
     * 	"scores":[821.0,821.0],</br>
     * 	"tags":["",""],</br>
     * 	"type":"SEARCH_RESULTS"</br>
     * }</code>
     *
     * @deprecated use {@link searchIndexPostJson}
     * @see http://pastec.io/doc/oss/ 
	 */
	public void searchIndexPost(File f) throws IOException {
		final Client client = ClientBuilder.newBuilder().build();
	    final WebTarget target = client.target(_uri);
		
		Response response = target.request().post(Entity.entity(f, MediaType.TEXT_PLAIN_TYPE));
	    
	    printResponse(response);
	    String result = response.readEntity(String.class);
	    System.out.println(result);
	}

	
	/**
	 * Search request
	 *
	 * This call performs a search in the index thanks to a request image. 
	 * It returns the id of the matched images from the most to the least relevant ones.
	 * <code>{</br>
     * 	"bounding_rects": [</br>
     * 		{"height":447,"width":318,"x":38,"y":97},</br>
     * 		{"height":447,"width":318,"x":38,"y":97}</br>
     * 	],</br>
     * 	</br>
     * 	"image_ids": [1,1234],</br>
     * 	"scores":[821.0,821.0],</br>
     * 	"tags":["",""],</br>
     * 	"type":"SEARCH_RESULTS"</br>
     * }</code>
	 * @return the result of the search
     *
     * @see http://pastec.io/doc/oss/ 
	 */
	public SearchResults searchIndexPostJson(File f) throws IOException {
		final Client client = ClientBuilderInstance.INSTANCE.build();
		
	    final WebTarget target = client.target(_uri);
	
	    SearchResults searchResults = target.request(MediaType.APPLICATION_JSON_TYPE)
	    		.post(Entity.entity(f, MediaType.TEXT_PLAIN_TYPE), SearchResults.class);
	    return searchResults;
	}

	private static void printResponse(final Response response) {
		Object entity = response.getEntity();
	    EntityTag entityTag = response.getEntityTag();
	    MediaType mediaType = response.getMediaType();
		System.out.println(response.getStatus());
		System.out.println(entity + " " + entityTag + " " + mediaType);
	}
	

}