// Server Code

package com.odbaas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;

@Path("/odbaas")
public class Server {
	@Context
	private ServletContext sctx;
	
        @GET
	@Path("mytest/{param}")
	public Response getMsg(@PathParam("param") String msg) 
        {
            System.out.println("Sbfdgn");
            //Database db = new Database();
            Login login = new Login("WhutisThis","There");
            String output = "Jersey say : " + login.getToken();
            
            return Response.status(200).entity(output).build();
        }
        
        @GET
	@Path("mytry/{param}")
	public Response tryMsg(@PathParam("param") String msg) 
        {
            System.out.println("try");
            //Database db = new Database();
            
            Validate validate = new Validate(msg);
            String output = "tryMsg say : " + validate.isValid();
            System.out.println(validate.getUser());
            return Response.status(200).entity(output).build();
        }
        
       	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response login(@FormParam("user_name") String userName, @FormParam("password") String password)
	{
            JSONObject obj = new JSONObject();
            Login login = new Login(userName,password);
            String token = login.getToken();
            
            if (!(token.length() > 0))
            {
                obj.put("error","User-name already exists or Password is Invalid.");
                obj.put("status",401);
                return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
            }
            obj.put("token",token);
            obj.put("status",200);
            return Response.status(Status.OK).entity(obj.toString()).build();
            //something
			
	}
	
	@Path("/table/create/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response createTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @FormParam("schema") String schema, @FormParam("primary_key") String pKey)
	{
            
            return null;
		//something
			
	}
	
	@Path("/table/insert/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response insertTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @FormParam("schema") String schema,@FormParam("data") String data)
	{
		return null;
		//something
			
	}
	
	/*
	@Path("/mytry")
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String mytry()
	{
		return "Hi there";
	}
	
	@Path("/select")
	@GET
	@Produces("application/json")
	public Response selectTable(@QueryParam("table_name") String tableName, @QueryParam("token") String token, @QueryParam("columns") String columns,@QueryParam("where") String where,@QueryParam("sort") String sort, @QueryParam("limit") String limit )
	{
		return null;
		//something
			
	}
	*/
	@Path("/select")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String selectTable()
	{
		return "Hi there";
		//something
			
	}
	@Path("/update/{table_name}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED )
	@Produces("application/json")
	public Response updateTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @QueryParam("columns") String columns,@QueryParam("where") String where,@QueryParam("sort") String sort, @QueryParam("limit") String limit )
	{
		return null;
		//something
			
	}
	
	@Path("/delete/{table_name}")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response deleteTable(@PathParam("table_name") String tableName, @FormParam("token") String token,@QueryParam("where") String where )
	{
		return null;
		//something
			
	}
	
	@Path("/drop/{table_name}")
        @DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response dropTable(@PathParam("table_name") String tableName, @FormParam("token") String token )
	{
		return null;
		//something
			
	}
	
}


