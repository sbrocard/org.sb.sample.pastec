package org.sb.sample.pastec.webapp.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.pastec.webapp.Controller;

@Path("/pastec")
public class PastecServiceRest 
{
	
	@Inject
	private Controller controller;

	@Path("/content")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ImageContentData searchContentData(InputStream uploadedInputStream) throws IOException {
		return controller.searchContentData(uploadedInputStream);
	}

}