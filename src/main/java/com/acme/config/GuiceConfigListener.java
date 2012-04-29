package com.acme.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceConfigListener extends GuiceServletContextListener {

	static {
		// workaround http://code.google.com/p/google-guice/issues/detail?id=488
		// Guice gives an info stack trace when starting inside AppEngine
		// TODO: still not working. Bah.
		Logger logger = Logger.getLogger("com.google.inject.internal.util.$FinalizableReferenceQueue");
		logger.setLevel(Level.WARNING);
	}
	
	@Override
    protected Injector getInjector() {
		Injector instance = Guice.createInjector(
			new MyServletModule(),
			new MyServicesModule(),
			new MyPersistenceModule()
		);
		return instance;
    }

}
