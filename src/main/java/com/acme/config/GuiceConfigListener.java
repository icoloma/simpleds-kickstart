package com.acme.config;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import com.acme.log.ErrorHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceConfigListener extends GuiceServletContextListener {
	
	@Override
    protected Injector getInjector() {
		// this is a workaround because AbstractContainerService.updateLoggingConfiguration() overwrites all handlers configuration
		// see line 434 at http://code.google.com/p/googleappengine/source/browse/trunk/java/src/main/com/google/appengine/tools/development/AbstractContainerService.java
		// also: http://code.google.com/p/googleappengine/issues/detail?id=7467
		
		// instanceof will not work here because of ClassLoaders mojo
		String cm = ErrorHandler.class.getName(); 
		for (Handler handler : Logger.getLogger("").getHandlers()) {
			if (handler.getClass().getName().equals(cm)) {
				handler.setLevel(Level.WARNING);
			}
		}
		
		// you can remove these. They are here just to check that 
		// the logs are configured properly
		LoggerFactory.getLogger(getClass()).debug("example debug message");
		LoggerFactory.getLogger(getClass()).info("example info message");
		LoggerFactory.getLogger(getClass()).warn("example warning message");

		Injector instance = Guice.createInjector(
			new MyServletModule(),
			new MyServicesModule(),
			new MyPersistenceModule()
		);
		return instance;
    }

}
