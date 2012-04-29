package com.acme.config;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.simpleds.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.view.Viewable;

/**
 * Thos should be used as last chance of handling exceptions.
 * It's better to just extend {@link WebApplicationException}, but that's
 * not always possible (specially with third-party jars)
 * @author icoloma
 */
@Provider
@Singleton
public class MyExceptionMapper implements ExceptionMapper<Throwable> {

	private static Logger log = LoggerFactory.getLogger(MyExceptionMapper.class);
	
	@Override
	public Response toResponse(Throwable root) {
		Throwable exception = root;
		if (exception instanceof WebApplicationException) {
			if (((WebApplicationException)exception).getResponse().getStatus() == 404) {
				// forward 404
				// workaround for http://code.google.com/p/googleappengine/issues/detail?id=7327
				return Response.status(Status.NOT_FOUND).entity(new Viewable("/error/404.jsp")).build();
			}
			return ((WebApplicationException)exception).getResponse();
		} else if (exception instanceof EntityNotFoundException) {
			return Response.status(Status.NOT_FOUND).build();
		} 
		log.error(exception.getMessage(), exception);
		while (exception != null) {
			if (exception instanceof RuntimeException) {
				exception = exception.getCause();
			} else {
				break;
			}
		}
		return Response.status(500).build();
	}

}
