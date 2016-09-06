package org.sb.sample.image.client;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {

	/**
	 * Labels
	 * required
	 */
	public List<Label> lbs;
	/**
	 * Actual link
	 * required
	 */
	public String lk;
	/**
	 * the type of link: 1 if webpage, 2 if video
	 * optional
	 * TODO sb, work out how to use an enum here
	 */
	public Integer t;
	
	@Override
	public String toString() {
		return "Link [lbs=" + lbs + ", lk=" + lk + ", t=" + t + "]";
	}
	
	
}
