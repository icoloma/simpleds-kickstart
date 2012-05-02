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

/**
 * This should be used as last chance of handling exceptions.
 * A better solution is to extend {@link WebApplicationException}, but that's
 * not always possible (specially with third-party classes)
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
