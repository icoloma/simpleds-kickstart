package com.acme.config;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.inject.AbstractModule;

/**
 * Configure service classes
 * @author icoloma
 *
 */
public class MyServicesModule extends AbstractModule {

	@Override
	protected void configure() {
		// You can use injection to address GAE services at your discretion 
		bind(MemcacheService.class).toInstance(MemcacheServiceFactory.getMemcacheService());
	}

}
