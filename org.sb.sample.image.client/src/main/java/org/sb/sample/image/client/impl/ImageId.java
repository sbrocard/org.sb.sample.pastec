package org.sb.sample.image.client.impl;

import org.sb.sample.image.client.IImageId;

public class ImageId implements IImageId {

	private String id;

	public ImageId(String id) {
		this.id = id;
	}
	
	@Override
	public String asString() {
		return id;
	}

}
