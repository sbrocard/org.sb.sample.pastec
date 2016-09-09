package org.sb.sample.pastec.client.impl;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sb.sample.pastec.client.ImageAdded;

public class ImageResourceMock {

	private String id;

	public ImageResourceMock(String id) {
		this.id = id;
	}

	// for the browser
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public Device getDeviceHTML() {
//		return findDevice();
//	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public ImageAdded putDevice(InputStream inputStream) {
		ImageAdded imageAdded = new ImageAdded();
		imageAdded.image_id = id;
		imageAdded.type = "IMAGE_ADDED";
		System.out.println("PastecServiceMock image added [" + id + "]");
		return imageAdded;
	}

//	@DELETE
//	public void deleteDevice() {
//		Device c = deviceService.deleteDevice(id);
//		if(c==null)
//			throw new RuntimeException("Delete: Device with " + id +  " not found");
//	}
/*
	private Response putAndGetResponse(Device device) {
		Response res;
		if(deviceService.existsDevice(device.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		deviceService.registerDevice(device);
		return res;
	}
*/
}
