package com.acme.service;

import org.simpleds.CursorList;

import com.acme.model.User;
import com.acme.service.impl.UsersServiceImpl;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Key;
import com.google.inject.ImplementedBy;

@ImplementedBy(UsersServiceImpl.class)
public interface UsersService {

	User get(Key userKey);

	User save(User user);
	
	void delete(Key userKey);
	
	CursorList<User> find(boolean includeDeleted, Cursor cursor, int pageSize);

	void reset();
	
}
