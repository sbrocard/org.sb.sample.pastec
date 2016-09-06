package org.sb.sample.pastec.webapp.rest;

import java.util.Collections;

import org.sb.sample.image.client.IImageService;
import org.sb.sample.image.client.ImageContentData;

final class ImageServiceMock implements IImageService {
	@Override
	public ImageContentData findContentData(String imageId) {
		if (imageId.equals(Utils.IMAGEID_1)) {
			return Utils.createImageContentData();
		} else	{
			ImageContentData contentData = new ImageContentData();
	    	contentData.lks = Collections.emptyList();

			return contentData;
		}
	}
}