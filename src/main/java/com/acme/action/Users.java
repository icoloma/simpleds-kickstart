package com.acme.action;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.simpleds.CursorList;

import com.acme.model.User;
import com.acme.service.UsersService;
import com.google.appengine.api.datastore.Key;
import com.sun.jersey.api.view.Viewable;

@Path("/users")
public class Users {

	@Inject
	private UsersService usersService;
	
	@GET @Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public CursorList<User> list(
			@QueryParam("includeDeleted") boolean includeDeleted,
			@QueryParam("cursor") CursorParameter cursor
			) {
		return usersService.find(includeDeleted, cursor == null? null : cursor.getValue(), 30);
	}
	
	@GET @Path("{key}")
	public Viewable get(@PathParam("key") KeyParameter keyParam) {
		Key userKey = keyParam.getValue(User.class);
		return new Viewable("/users/view.jsp", usersService.get(userKey));
	}
	
	/**
	 * This would be oh-so-much-simpler with Backbone.js and the like.
	 * Anyway, I don't want to get too much meat into a demo application.
	 */
	@POST @Path("{key}") 
	public Response post(
			@PathParam("key") KeyParameter keyParam,
			@FormParam("name") String name,
			@FormParam("description") String description
			) throws URISyntaxException {
		User user = new User();
		user.setKey(keyParam == null? null : keyParam.getValue(User.class));
		user.setName(name);
		user.setDescription(description);
		usersService.save(user);
		return Response.seeOther(new URI("/users/" + user.getKey().getId())).build();
	}
	
	/**
	 * Reset the datastore to a default data set
	 * @return
	 */
	@GET @Path("/reset")
	public String reset() {
		usersService.reset();
		return "Datastore restored to initial state";
	}
		
}
