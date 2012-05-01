package com.acme.model;

/**
 * Attributes of persistent classes.
 * Keeping these in the same class promotes reuse between persistent classes. 
 * @author icoloma
 */
public interface Attrs {

	// You should try to keep these sorted by VALUE. 
	// When something fails, you will come here looking for answers
	
	static final String DELETED = "d";
	static final String DESCRIPTION = "de";
	static final String EXTRA = "e";
	static final String KEY = "k";
	static final String NAME = "n";
	
}
