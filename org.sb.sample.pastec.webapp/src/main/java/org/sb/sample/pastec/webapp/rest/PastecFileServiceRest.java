package org.sb.sample.pastec.webapp.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.sb.sample.image.client.ImageContentData;
import org.sb.sample.pastec.webapp.Controller;

@Path("/pastecfile")
public class PastecFileServiceRest 
{
	
	private final Controller controller;

	@Inject
	public PastecFileServiceRest(Controller controller) {
		this.controller = controller;
	}

	// TODO sb missing unit test
	@Path("/content")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public ImageContentData searchContentData(@FormDataParam("file") InputStream uploadedInputStream) throws IOException {
		return controller.searchContentData(uploadedInputStream);
	}
}