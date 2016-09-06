package org.sb.sample.image.client;

public interface IImageService {

	/**
	 * Find the content data for a given image id
	 * @param imageId
	 * @return the ImageContentData object if any, or <code>null</code> if image is found
	 */
	ImageContentData findContentData(String imageId);

}
