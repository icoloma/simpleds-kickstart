package com.acme.config;

import java.util.Set;

import com.acme.action.Root;
import com.acme.action.Users;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * Configure web stuff
 * @author icoloma
 *
 */
@SuppressWarnings("rawtypes")
public class MyServletModule extends ServletModule {

	@Override
	@SuppressWarnings("unchecked")
    protected void configureServlets() {
		
		// bind resources
		for (Class resourceClass : getResources()) {
        	bind(resourceClass);
		}
		
		// Exception mapping
		bind(MyExceptionMapper.class);
		
		// jersey customization
        serve("/*").with(
        	GuiceContainer.class, 
        	ImmutableMap.<String, String>builder()
        	
        		// all files relative to /WEB-INF/jsp 
	    		.put("com.sun.jersey.config.property.JSPTemplatesBasePath", "/WEB-INF/jsp")
	    		
	    		// workaround for http://java.net/jira/browse/JERSEY-630
	    		.put("com.sun.jersey.config.feature.DisableWADL", "true") 
	    		
	    		// use Jackson to serialize
	    		.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true") 
	    		
	    		.build()		
		);
        
    }
	
	private Set<Class> getResources() {
		return Sets.newHashSet(new Class[] {
			// give this to Jersey to serialize/deserialize JSON
			ObjectMapperContextResolver.class,
			
			// resource classes
			Root.class,
			Users.class
		});
	}

}
