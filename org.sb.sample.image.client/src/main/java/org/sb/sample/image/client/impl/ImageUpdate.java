package org.sb.sample.image.client.impl;

import java.util.Date;

import org.sb.sample.image.client.IImageId;
import org.sb.sample.image.client.IImageUpdate;

public class ImageUpdate implements IImageUpdate {

	private final IImageId imageId;
	private final Kind updateKind;
	private final Date time;

	public ImageUpdate(IImageId imageId, Kind updateKind, Date time) {
		this.updateKind = updateKind;
		this.imageId = imageId;
		this.time = time;
	}

	@Override
	public IImageId getImageId() {
		return imageId;
	}

	@Override
	public Kind getUpdateKind() {
		return updateKind;
	}

	@Override
	public Date getTime() {
		return time;
	}

}
