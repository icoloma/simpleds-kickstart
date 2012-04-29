package com.acme.model;

import static com.acme.model.Attrs.DELETED;
import static com.acme.model.Attrs.DESCRIPTION;
import static com.acme.model.Attrs.KEY;
import static com.acme.model.Attrs.NAME;

import java.util.Random;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.simpleds.KeyFactory2;
import org.simpleds.annotations.Cacheable;
import org.simpleds.annotations.Id;
import org.simpleds.annotations.Property;
import org.simpleds.converter.StringToTextConverter;

import com.acme.util.LoremIpsum;
import com.google.appengine.api.datastore.Key;

@Cacheable
@JsonIgnoreProperties("key")
public class User {

	@Id @Property(KEY)
	private Key key;
	
	@Property(required=true, value=NAME)
	private String name;
	
	@Property(unindexed=true, converter=StringToTextConverter.class, value=DESCRIPTION)
	@JsonProperty
	private String description;
	
	@Property(required=true, value=DELETED)
	private boolean deleted;

	// JSON get key value
	public long getId() {
		return key == null? null : key.getId();
	}
	
	// JSON set key value
	public void setId(long id) {
		key = KeyFactory2.createKey(User.class, id);
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public static User createExampleUser() {
		User user = new User();
		user.setName(LOREM_IPSUM.getWords(Math.abs(3 + RANDOM.nextInt(40))));
		user.setDescription(LOREM_IPSUM.getParagraphs(2));
		return user;
	}
	
	private static LoremIpsum LOREM_IPSUM = new LoremIpsum();
	private static Random RANDOM = new Random();
	
}
