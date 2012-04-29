package com.acme.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.simpleds.CursorList;
import org.simpleds.EntityManager;
import org.simpleds.annotations.Transactional;

import com.acme.model.User;
import com.acme.service.UsersService;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class UsersServiceImpl implements UsersService {

	@Inject
	private EntityManager entityManager;

	private static Predicate<User> NOT_DELETED = new Predicate<User>() {

		@Override
		public boolean apply(User input) {
			return !input.isDeleted();
		}
	};
	
	@Override
	public User get(Key userKey) {
		return entityManager.get(userKey);
	}
	
	/**
	 * Example of use of transactions. Notice that in this case transactions 
	 * are not required.
	 */
	@Override
	@Transactional
	public User save(User transientUser) {
		Transaction tx = entityManager.beginTransaction();
		User persistentUser = entityManager.get(tx, transientUser.getKey());
		persistentUser.setName(transientUser.getName());
		persistentUser.setDescription(transientUser.getDescription());
		entityManager.put(tx, persistentUser);
		return persistentUser;
	}
	
	/**
	 * Example of use of {@link CursorList} with {@link Predicate}.
	 */
	@Override
	public CursorList<User> find(boolean includeDeleted, Cursor cursor, int pageSize) {
		return entityManager.createQuery(User.class)
				// less indexes saves trees
				// .equal(Attrs.DELETED, false)
				.withPredicate(includeDeleted? null : NOT_DELETED)
				.withStartCursor(cursor)
				.asCursorList(pageSize)
				;
	}
	
	@Override
	public void reset() {
		// delete all existing users 
		Iterable<Key> userKeys = entityManager.createQuery(User.class)
			.keysOnly()
			.asIterable();
		entityManager.delete(userKeys);
		
		// insert new Users in a single batch operation
		int numUsers = 100;
		List<User> users = Lists.newArrayListWithCapacity(numUsers);
		for (int i = 0; i < numUsers; i++) {
			User user = User.createExampleUser();
			user.setDeleted(i % 7 == 0);
			users.add(user);
		}
		entityManager.put(users);
	}
	
}
