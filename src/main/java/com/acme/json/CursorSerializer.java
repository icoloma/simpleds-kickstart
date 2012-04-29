package com.acme.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.google.appengine.api.datastore.Cursor;

public class CursorSerializer extends JsonSerializer<Cursor> {
	
	@Override
	public void serialize(Cursor cursor, JsonGenerator jgen,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jgen.writeString(cursor.toWebSafeString());
	}

}
