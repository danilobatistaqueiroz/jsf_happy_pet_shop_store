package com.labs.jsf.rest;

import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import com.labs.jsf.model.Product;

//@ApplicationPath("/Rest")
@Path("/admin")
public class UsersService extends Application {

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/activeUsers")
	@Produces("application/json")
	public Product activeUsers() {
		if (request.isUserInRole("admin")) {
			Product item = new Product(10,"product10","its amazing product and beautiful",15.11);
			return item;
		} else {
			return null;
		}
	}
}
