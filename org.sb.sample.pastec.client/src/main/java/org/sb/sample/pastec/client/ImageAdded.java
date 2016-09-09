package org.sb.sample.pastec.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImageAdded {

	public String image_id;
	public String type;
	@Override
	public String toString() {
		return "ImageAdded [image_id=" + image_id + ", type=" + type + "]";
	}
	
	
}
