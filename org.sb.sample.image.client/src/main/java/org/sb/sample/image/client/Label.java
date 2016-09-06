package org.sb.sample.image.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Label {

	/**
	 * The language of the label based on ISO 639-1
	 * required
	 */
	public String lg;
	
	/**
	 * The label in that language
	 * required
	 */
	public String l;

	@Override
	public String toString() {
		return "Label [lg=" + lg + ", l=" + l + "]";
	}
	
	
}
