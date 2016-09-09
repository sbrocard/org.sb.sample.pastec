package org.sb.sample.image.client;

/**
 * This interface defines the id of an image.
 * 
 * @author sbrocard
 *
 */
public interface IImageId {

	/** 
	 * Returns the string representation of the id
	 * @return
	 */
	String asString();
	
	@Override
	boolean equals(Object obj);
	
	@Override
	int hashCode();
}
