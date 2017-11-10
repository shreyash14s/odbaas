// Server Code

package com.odbaas;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;


@Path("/odbaas")
public class Server {
	@Context
	private ServletContext sctx;
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response login(@FormParam("user_name") String userName, @FormParam("password") String password)
	{
		//something
			
	}
	
	@Path("/table/create/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response createTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @FormParam("schema") String schema, @FormParam("primary_key") String pKey)
	{
		//something
			
	}
	
	@Path("/table/insert/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response insertTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @FormParam("schema") String schema,@FormParam("data") String data)
	{
		//something
			
	}
	
	@Path("/select")
	@GET
	@Produces("application/json")
	public Response selectTable(@QueryParam("table_name") String tableName, @QueryParam("token") String token, @QueryParam("columns") String columns,@QueryParam("where") String where,@QueryParam("sort") String sort, @QueryParam("limit") String limit )
	{
		//something
			
	}
	
	
	@Path("/update/{table_name}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED )
	@Produces("application/json")
	public Response updateTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @QueryParam("columns") String columns,@QueryParam("where") String where,@QueryParam("sort") String sort, @QueryParam("limit") String limit )
	{
		//something
			
	}
	
	@Path("/delete/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response deleteTable(@PathParam("table_name") String tableName, @FormParam("token") String token,@QueryParam("where") String where )
	{
		//something
			
	}
	
	@Path("/drop/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response dropTable(@PathParam("table_name") String tableName, @FormParam("token") String token )
	{
		//something
			
	}
	
}


