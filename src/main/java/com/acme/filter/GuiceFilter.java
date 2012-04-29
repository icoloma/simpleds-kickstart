package com.acme.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Do not pass _ah requests to Guice
 * @author icoloma
 */
public class GuiceFilter extends com.google.inject.servlet.GuiceFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getRequestURI().startsWith("/_ah")) {
			filterChain.doFilter(req, response);
		} else {
			super.doFilter(req, response, filterChain);
		}
	}
	
}
