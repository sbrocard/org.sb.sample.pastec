package org.sb.sample.pastec.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
	SearchResults searchIndexPostJson(InputStream input) throws IOException;

}
