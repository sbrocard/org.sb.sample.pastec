package org.sb.sample.pastec.webapp.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.sb.sample.image.client.IImageService;
import org.sb.sample.pastec.client.IPastecService;
import org.sb.sample.pastec.client.SearchResults;

/**
 * This service is used by the index.html file for manula test purposes
 * @author sbrocard
 *
 */
@Path("/pastecMultiform")
public class MultiformPastecServiceRest 
{
	
	private IPastecService pastecService;

	@Inject
	public MultiformPastecServiceRest(IPastecService pastecService, IImageService imageService) {
		this.pastecService = pastecService;
	}
	
	@POST
	@Path("/search")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response search(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{
	    String UPLOAD_PATH = "/tmp/";
	    try
	    {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        File file = new File(UPLOAD_PATH + fileMetaData.getFileName());
			OutputStream out = new FileOutputStream(file);
	        while ((read = fileInputStream.read(bytes)) != -1)
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        
		    SearchResults searchResults = pastecService.searchIndexPostJson(file);
		    System.out.println(searchResults);
		    if (searchResults.getScores().size() > 0) {
			    return Response.ok("image recognised !! Score=" + searchResults.getScores().get(0)).build();
		    }
	    } catch (IOException e)
	    {
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return Response.ok("image not recognised !!").build();
	}
}