package org.sb.sample.pastec.client;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * 
 * @see http://pastec.io/doc/oss/ 
 */
@XmlRootElement
public class BoundingRect {

	public int x;
	public int y;
	public int height;
	public int widht;

	public BoundingRect() {
	}

	@Override
	public String toString() {
		return "BoundingRect [x=" + x + ", y=" + y + ", height=" + height + ", widht=" + widht + "]";
	}
}
