package org.sb.sample.image.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sb.sample.image.client.impl.ImageDataContentJsonReader;

public class ImageContentDataTest {

	@Test
	public void testJsonUnmarshalling() {
		ImageContentData imageContentData = ImageDataContentJsonReader.readJson();
		assertEquals("ImageContentData "
				+ "[lks=[Link [lbs=["
				+ "Label [lg=EN, l=Interview], "
				+ "Label [lg=FR, l=Entretien]], "
				+ "lk=http://the_url_of_interview_video_to display, t=2], "
				+ "Link [lbs=["
				+ "Label [lg=EN, l=Documentary], "
				+ "Label [lg=FR, l=Documentaire]], "
				+ "lk=http://the_url_of_documentary_video_to display, t=2], "
				+ "Link [lbs=["
				+ "Label [lg=EN, l=Information], "
				+ "Label [lg=FR, l=Informations]], "
				+ "lk=http://the_url_the_web_page_to display, t=1]]]", imageContentData.toString());
	}
}
