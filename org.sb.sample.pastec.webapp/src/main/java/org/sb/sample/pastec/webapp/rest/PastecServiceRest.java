package org.sb.sample.pastec.webapp.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sb.sample.image.client.IImageService;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.SearchResults;

@Path("/pastec")
public class PastecServiceRest 
{
	
	/**
	 * TODO sb, 500 is totally arbitrary, we should study the eaxct meaning of that value.
	 */
	private static final int MINIMUM_SCORE = 500;
	private IPastecService pastecService;
	private IImageService imageService;

	@Inject
	public PastecServiceRest(IPastecService pastecService, IImageService imageService) {
		this.pastecService = pastecService;
		this.imageService = imageService;
	}
	
	@Path("/content")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public ImageContentData searchContentData(InputStream uploadedInputStream) throws IOException {
	    SearchResults searchResults = pastecService.searchIndexPostJson(uploadedInputStream);
	    System.out.println(searchResults);
	    
	    ImageContentData contentData = null;
	    if (searchResults.getScores().size() > 0 && searchResults.getScores().get(0) > MINIMUM_SCORE) {
		    String imageId = searchResults.getImage_ids().get(0);
		    contentData = imageService.findContentData(imageId);
	    }
	    // No data, or no image, then create an empty result
	    if (contentData == null) {
	    	contentData = new ImageContentData();
	    	contentData.lks = Collections.emptyList();
	    }
	    System.out.println(contentData);
		return contentData;
	}
	
}