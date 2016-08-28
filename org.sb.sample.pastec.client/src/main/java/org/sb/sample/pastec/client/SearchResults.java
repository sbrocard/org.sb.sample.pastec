package org.sb.sample.pastec.client;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Search results
 *
 * <code>{</br>
 * 	"bounding_rects": [</br>
 * 		{"height":447,"width":318,"x":38,"y":97},</br>
 * 		{"height":447,"width":318,"x":38,"y":97}</br>
 * 	],</br>
 * 	</br>
 * 	"image_ids": [1,1234],</br>
 * 	"scores":[821.0,821.0],</br>
 * 	"tags":["",""],</br>
 * 	"type":"SEARCH_RESULTS"</br>
 * }</code>
 * 
 * @see http://pastec.io/doc/oss/ 
 */
@XmlRootElement
public class SearchResults {

	private List<BoundingRect> boundingRects;
	private List<String> imageIds;
	private List<Double> scores;
	private List<String> tags;

	public SearchResults() {
	}

	@Override
	public String toString() {
		return "SearchResults [boundingRects=" + boundingRects + ", imageIds=" + imageIds + ", scores=" + scores
				+ ", tags=" + tags + "]";
	}
	
	@XmlElement(name="image_ids")
	public List<String> getImage_ids() {
		return imageIds;
	}

//	@XmlElement(name="image_ids")
	public void setImage_ids(List<String> image_ids) {
		this.imageIds = image_ids;
	}

	public List<Double> getScores() {
		return scores;
	}

	public void setScores(List<Double> score) {
		this.scores = score;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@XmlElement(name="bounding_rects")
	public List<BoundingRect> getBoundingRects() {
		return boundingRects;
	}

//	@XmlElement(name="bounding_rects")
	public void setBoundingRects(List<BoundingRect> boundingRects) {
		this.boundingRects = boundingRects;
	}
}
