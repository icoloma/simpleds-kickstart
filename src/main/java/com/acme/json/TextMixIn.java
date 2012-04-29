package com.acme.json;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.appengine.api.datastore.Text;

/**
 * Mixin used to serialize {@Text Text} instances using Jackson.
 * To use, invoke
 * <pre>
 * objectMapper.getSerializationConfig().addMixInAnnotations(Text.class, TextJacksonMixIn.class);
 * </pre>
 * Details about mixins in Jackson can be seen <a href="http://wiki.fasterxml.com/JacksonMixInAnnotations">here</a>
 * @author icoloma
 *
 */
@JsonSerialize(using=TextSerializer.class)
@JsonDeserialize(using=TextDeserializer.class, as=Text.class)
public class TextMixIn {

	// empty on purpose
	
}
