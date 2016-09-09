package org.sb.sample.image.client;

import java.util.List;

import rx.Observable;

/**
 * This interface intends to provide the application
 * with features that allow to query, manipulate the image referential.
 * 
 * It intends to provide low-level features to manage the referential.
 * 
 * The API is base on RxJava and is asynchronous 
 * 
 * @author sbrocard
 *
 */
public interface IImageReferentialService {
	
	final String SEARCH_CRITERIA_ALL = "all";

	/**
	 * Returns the list of the images that match the given searchCriteria.
	 * {@link #SEARCH_CRITERIA_ALL} is a special criteria that must return all the images
	 * 
	 * The returned observable is supposed to complete at some point at the end of the search.
	 *  
	 * @param searchCriteria criteria of the search, implementation specific
	 * @return an observable of list of images
	 */
	Observable<List<IImage>> requestImages(String searchCriteria);
	
	/**
	 * Observe the updates of the referential regarding the images that match with the given searchCriteria.
	 * The return observable is not supposed to complete unless when the referential is closed.
	 * The caller must dispose of the connection itself when it is not needed any longer.
	 *  
	 * @param searchCriteria criteria of the search, implementation specific
	 * @return an observable for the given search criteria
	 */
	Observable<IImageUpdate> observeImageUpdates(String searchCriteria);
	
	
}
