package com.acme.json;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.appengine.api.datastore.Cursor;

/**
 * Mixin used to serialize {@link Cursor} instances using Jackson.
 * @author icoloma
 *
 */
@JsonSerialize(using=CursorSerializer.class)
@JsonDeserialize(using=CursorDeserializer.class, as=Cursor.class)
public class CursorMixIn {

	// empty on purpose
	
}
