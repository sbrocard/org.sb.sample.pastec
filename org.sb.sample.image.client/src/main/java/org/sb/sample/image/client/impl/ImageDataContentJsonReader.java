package org.sb.sample.image.client.impl;

import java.io.InputStream;

import javax.ws.rs.ProcessingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.sb.sample.image.client.ImageContentData;

public class ImageDataContentJsonReader {

	
	public static ImageContentData readJson() {
		try {
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(ImageContentData.class);
			// Create the Unmarshaller Object using the JaxB Context
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			// Set the Unmarshaller media type to JSON or XML
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,
					"application/json");
			// Set it to true if you need to include the JSON root element in the
			// JSON input
			unmarshaller
			.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
			// Create the StreamSource by creating StringReader using the JSON input
			InputStream inputstream = ImageContentData.class.getResourceAsStream("ImageContentDataToStringTest.json");
			StreamSource json = new StreamSource(inputstream);

			// Getting the ImageContentData pojo again from the json
			ImageContentData imageContentData = unmarshaller.unmarshal(json, ImageContentData.class)
					.getValue();
			return imageContentData;
		} catch (JAXBException jaxbException) {
			throw new ProcessingException("Error deserializing a ImageContentData.",
					jaxbException);
		}
	}
}
