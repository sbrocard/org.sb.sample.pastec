package org.sb.sample.pastec.client.impl.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.sb.sample.pastec.client.SearchResults;

@Provider
@Consumes("application/octet-stream")
public class MyMoxyJSONProvider	extends MOXyJsonProvider {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return super.isReadable(type, genericType, annotations, mediaType) || type == SearchResults.class;
	}
}