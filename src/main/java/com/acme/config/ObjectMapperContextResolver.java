package com.acme.config;

import javax.inject.Singleton;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.acme.json.CursorMixIn;
import com.acme.json.TextMixIn;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Text;

@Provider
@Singleton
public final class ObjectMapperContextResolver implements ContextResolver<ObjectMapper>, com.google.inject.Provider<ObjectMapper> {
	
	private static ObjectMapper objectMapper;
	
	private static synchronized void initInstance() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
			DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();
			
			// example: serialize/deserialize Text attributes as String
			serializationConfig.addMixInAnnotations(Cursor.class, CursorMixIn.class);
			deserializationConfig.addMixInAnnotations(Cursor.class, CursorMixIn.class);
			serializationConfig.addMixInAnnotations(Text.class, TextMixIn.class);
			deserializationConfig.addMixInAnnotations(Text.class, TextMixIn.class);
		}
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return get();
	}

	@Override
	@Singleton
	public ObjectMapper get() {
		if (objectMapper == null) {
			initInstance();
		}
		return objectMapper;
	}
	
}
