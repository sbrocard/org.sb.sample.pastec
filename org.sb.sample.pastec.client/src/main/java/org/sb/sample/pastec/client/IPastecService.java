package org.sb.sample.pastec.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;

/**
 * @see http://pastec.io/doc/oss/ 
 */
public interface IPastecService {

	/**
	 * Search request
	 *
	 * This call performs a search in the index thanks to a request image. 
	 * It returns the id of the matched images from the most to the least relevant ones.
	 * 
	 * @return the result of the search
     * @see http://pastec.io/doc/oss/ 
	 */
	SearchResults searchIndexPostJson(File f) throws IOException;

	/**
	 * Search request
	 *
	 * This call performs a search in the index thanks to a request image. 
	 * It returns the id of the matched images from the most to the least relevant ones.
	 * 
	 * @return the result of the search
     * @see http://pastec.io/doc/oss/ 
	 */
	SearchResults searchIndexPostJson(InputStream inputstream) throws IOException;

	/**
	 * Add an image to the Pastec service.
	 * The caller must subscribe to the observer to execute the call.
	 * @param id id of the image
	 * @param inputstream inputstream with the image
	 * @return an observable with the ImageAdded response from Pastec
	 */
	Observable<ImageAdded> addImage(String id, InputStream inputstream);

}
