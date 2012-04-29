package com.acme.action;

import com.google.appengine.api.datastore.Cursor;

/**
 * {@link Cursor} wrapper for Jersey 
 * @author icoloma
 */
public class CursorParameter {

	private final Cursor cursor;
	
	public CursorParameter(String serializedValue) {
		cursor = serializedValue != null? Cursor.fromWebSafeString(serializedValue) : null;
	}
	
	public Cursor getValue() {
		return cursor;
	}
	
}
