package org.sb.sample.pastec.client;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import javax.ws.rs.ProcessingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.Test;
import org.sb.sample.pastec.client.SearchResults;

public class SearchResultsTest {

	@Test
	public void testJsonUnmarshalling() {
		try {
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(SearchResults.class);
			// Create the Unmarshaller Object using the JaxB Context
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			// Set the Unmarshaller media type to JSON or XML
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,
					"application/json");
			// Set it to true if you need to include the JSON root element in the
			// JSON input
			unmarshaller
			.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
			// Create the StreamSource by creating StringReader using the JSON input
			InputStream inputstream = SearchResults.class.getResourceAsStream("SearchResultsToStringTest.json");
			StreamSource json = new StreamSource(inputstream);

			// Getting the SearchResults pojo again from the json
			SearchResults searchResults = unmarshaller.unmarshal(json, SearchResults.class)
					.getValue();
			assertEquals("SearchResults [boundingRects=[BoundingRect [x=38, y=97, height=447, widht=0], BoundingRect [x=38, y=97, height=447, widht=0]], imageIds=[1, 1234], scores=[821.0, 821.0], tags=[, ]]",  searchResults.toString());
		} catch (JAXBException jaxbException) {
			throw new ProcessingException("Error deserializing a SearchResults.",
					jaxbException);
		}
	}
}
