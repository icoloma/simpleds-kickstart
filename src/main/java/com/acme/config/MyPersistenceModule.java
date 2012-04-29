package com.acme.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.simpleds.guice.SimpledsModule;

import com.acme.model.User;
import com.google.inject.matcher.Matchers;

/** 
 * Configure persistence
 * @author icoloma
 */
public class MyPersistenceModule extends SimpledsModule {

	@Override
	protected void configure() {
		this.withPersistentClasses(getPersistentClasses())
			.withTransactionsFor(Matchers.inSubpackage("com.acme.service.impl"));
		
    	// use @AsJSON for persistence
		bind(ObjectMapper.class).toProvider(new ObjectMapperContextResolver());

		super.configure();
	}

	@SuppressWarnings("rawtypes")
	private Class[] getPersistentClasses() {
		return new Class[] {
			User.class,
		};
	}

}
