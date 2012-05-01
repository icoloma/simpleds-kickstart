package com.acme.action;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class Root {
	
	@GET
	public Viewable index() {
		return new Viewable("/index.jsp");
	}
	
}
