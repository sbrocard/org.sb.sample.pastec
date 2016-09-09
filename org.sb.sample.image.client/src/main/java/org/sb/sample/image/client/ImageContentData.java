package org.sb.sample.image.client;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImageContentData {

	/**
	 * Links
	 */
	public List<Link> lks;

	@Override
	public String toString() {
		return "ImageContentData [lks=" + lks + "]";
	}
	
	

}
