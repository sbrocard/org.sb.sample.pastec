package org.sb.sample.image.client;

import java.util.Date;

/**
 * Defines an updated that happened in the {@link IImageReferentialService}
 * It may be and addition, a removal or an update of image
 * @author sbrocard
 *
 */
public interface IImageUpdate {

	enum Kind {
		ADD,
		REMOVE,
		UPDATE
	}
	
	/** Returns the id of the image that is 
	 * concerned by the update.
	 * @return imageId instance
	 */
	IImageId getImageId();
	
	/**
	 * @return the update kind represented by the update
	 */
	Kind getUpdateKind();
	
	/**
	 * @return the date and the time of when the update happened
	 */
	Date getTime();
}
