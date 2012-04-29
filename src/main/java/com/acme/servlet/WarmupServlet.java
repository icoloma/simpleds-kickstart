package com.acme.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Warmup request handler
 * @author icoloma
 */
public class WarmupServlet extends HttpServlet { 

	private static Logger log = LoggerFactory.getLogger(WarmupServlet.class);
    
    @Override
	public void service(ServletRequest req, ServletResponse resp) 
            throws ServletException, IOException { 
        log.info("Warmup request received"); 
        HttpServletResponse response = (HttpServletResponse)resp;
		response.setStatus(200);
		response.getWriter().print("OK. Got it.");
    } 

}