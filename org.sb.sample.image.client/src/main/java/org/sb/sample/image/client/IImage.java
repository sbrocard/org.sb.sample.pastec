package org.sb.sample.image.client;

import java.io.InputStream;

import rx.Observable;

/**
 * This interface defines a container of an image.
 * It gives access to the content of the image plus to other 
 * information such as the  <b>id</b> of the image.
 * 
 * The access to the content of the image is asynchronous, so that it
 * allows to use a lazy mechanism to actually download the data.
 * 
 * @author sbrocard
 *
 */
public interface IImage {

	IImageId getImageId();
	
	/** 
	 * Returns an input stream instance with the content of the image.
	 * The returned input stream has to be closed by the caller when its reading job is done.
	 * @return an observable with one element.
	 */
	Observable<InputStream> getContent();
}
