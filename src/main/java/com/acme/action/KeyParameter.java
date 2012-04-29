package com.acme.action;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.simpleds.KeyFactory2;

import com.google.appengine.api.datastore.Key;

/**
 * {@link Key} wrapper for Jersey 
 * @author icoloma
 */
public class KeyParameter {

	private final String serializedValue;

	public KeyParameter(String serializedValue) {
		this.serializedValue = serializedValue;
	}
	
	public Key getValue(Class<?> persistentClass) {
		try {
			return KeyFactory2.createKey(persistentClass, Long.valueOf(serializedValue, 10));
		} catch (NumberFormatException e) {
			throw new WebApplicationException(e, Status.NOT_FOUND);
		}
	}

	public String getSerializedValue() {
		return serializedValue;
	}
	
}
