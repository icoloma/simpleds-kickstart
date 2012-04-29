package com.acme.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.google.appengine.api.datastore.Cursor;

public class CursorDeserializer extends JsonDeserializer<Cursor> {

	@Override
	public Cursor deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
        JsonToken curr = jp.getCurrentToken();
        // Usually should just get string value:
        if (curr == JsonToken.VALUE_STRING || curr.isScalarValue()) {
            return Cursor.fromWebSafeString(jp.getText());
        }
        throw ctxt.mappingException(Cursor.class);
	}

}
