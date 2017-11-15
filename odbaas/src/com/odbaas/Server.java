// Server Code

package com.odbaas;

import java.sql.SQLException;
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

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/odbaas")
public class Server {
	@Context
	private ServletContext sctx;
	
	@GET
	@Path("mytest/{param}")
	public Response getMsg(@PathParam("param") String msg) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Sbfdgn");
		//Database db = new Database();
		Login login = new Login("WhutisThis", "There"); //Handle exceptions
		String output = "Jersey say : " + login.getToken();
		
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("mytry/{param}")
	public Response tryMsg(@PathParam("param") String msg) throws ClassNotFoundException, SQLException 
	{
		System.out.println("try");
		//Database db = new Database();
		
		Validate validate = new Validate(msg);
		String output = "tryMsg say : " + validate.isValid();
		System.out.println(validate.getUser());
		return Response.status(200).entity(output).build();
	}
	
	
	@GET
	@Path("myinsert/{param}")
	public Response myInsert(@PathParam("param") String msg) throws ClassNotFoundException, SQLException 
	{
		
		CRUD crud = new CRUD("WhutisThis", "emp");
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("id",5);
		obj.put("name","durg");
		array.put(obj);
		JSONObject obj2 = new JSONObject();
		obj2.put("id",6);
		obj2.put("name","kam");
		array.put(obj2);
		//Database db = new Database();
		crud.insertTable(array.toString());
		String output = "tryMsg say : ";
		//System.out.println(validate.getUser());
		return Response.status(200).entity(output).build();
	}
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response login(@FormParam("user_name") String userName, @FormParam("password") String password)
	{
		JSONObject obj = new JSONObject();
		Login login;
		try {
			login = new Login(userName,password);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj.put("error",e.getMessage());
			obj.put("status",300);
			return Response.status(Status.BAD_GATEWAY).entity(obj.toString()).build();
		}
		
		//something
		
		
	}
	
	@Path("/table/create/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response createTable(@PathParam("table_name") String tableName, @FormParam("token") String token, @FormParam("schema") String schema, @FormParam("primary_key") String pKey)
	{
		
		Validate validate;
		JSONObject obj = new JSONObject();
		try {
			validate = new Validate(token);
			
			
			
			if( !validate.isValid() )
			{
				obj.put("error","Token is Invalid.");
				obj.put("status",401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			CRUD crud;
			crud = new CRUD(user,tableName);
			crud.createTable(schema, pKey);
			
			obj.put("success","Table is created.");
			obj.put("status",200);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
			
		} catch (ClassNotFoundException e1) {
			obj.put("error",e1.getMessage());
			obj.put("status",500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		} catch (SQLException e1) {
			obj.put("error",e1.getMessage());
			obj.put("status",500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
			
		}
		
		
		
		
		//something
		
		
	}
	
	@Path("/table/insert/{table_name}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response insertTable(@PathParam("table_name") String tableName, 
	@FormParam("token") String token, @FormParam("data") String data) {
		
		Validate validate;
		JSONObject obj = new JSONObject();
		
		try {
			validate = new Validate(token);
			if (!validate.isValid()) {
				obj.put("error", "Token is Invalid.");
				obj.put("status", 401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			CRUD crud = new CRUD(user,tableName);
			int rows = crud.insertTable(data);
			obj.put("success", rows + " Rows affected");
			obj.put("status", 200);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
		} catch (ClassNotFoundException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		} catch (SQLException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
		}
	}
	
	/*
	@Path("/mytry")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String mytry()
	{
		return "Hi there";
	}
	*/
	
	@Path("/select")
	@GET
	@Produces("application/json")
	public Response selectTable(@QueryParam("table_name") String tableName, 
	@QueryParam("token") String token, @QueryParam("columns") String columns,
	@QueryParam("where") String where, @QueryParam("sort_cols") String sortCols,
	@QueryParam("sort_order") String sortOrder, @QueryParam("limit") String limit) {
		
		JSONObject obj = new JSONObject();
		try {
			Validate validate = new Validate(token);
			JSONObject res = new JSONObject();
			CRUD crud;
			if (!validate.isValid()) {
				obj.put("error","Token is Invalid.");
				obj.put("status",401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			crud = new CRUD(user, tableName);
			
			JSONArray values = crud.selectTable(columns, where, sortCols, sortOrder, limit);
			
			res.put("length", values.length());
			res.put("values", values);
			
			obj.put("success", "Select is done.");
			obj.put("status", 200);
			obj.put("res", res);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
		} catch (ClassNotFoundException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		} catch (SQLException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
		}
	}
	
	/*
	@Path("/select")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String selectTable() {
		return "Hi there";
		//something
	}
	*/
	
	@Path("/update/{table_name}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response updateTable(@PathParam("table_name") String tableName, 
	@FormParam("token") String token, @FormParam("set") String set, 
	@FormParam("where") String where) {
		
		Validate validate;
		JSONObject obj = new JSONObject();
		try {
			validate = new Validate(token);
			if (!validate.isValid()) {
				obj.put("error", "Token is Invalid.");
				obj.put("status", 401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			CRUD crud = new CRUD(user, tableName);
			int rows = crud.updateTable(tableName, set, where);
			obj.put("success", rows + " Rows affected");
			obj.put("status", 200);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
			
		} catch (ClassNotFoundException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		} catch (SQLException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
		}
		//something
	}
	
	@Path("/delete/{table_name}")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response deleteTable(@PathParam("table_name") String tableName, 
				@FormParam("token") String token, @FormParam("where") String where) {
		Validate validate;
		JSONObject obj = new JSONObject();
		try {
			validate = new Validate(token);
			if (!validate.isValid()) {
				obj.put("error","Token is Invalid.");
				obj.put("status",401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			CRUD crud;
			crud = new CRUD(user,tableName);
			int res=crud.deleteTable(tableName, where);
			
			obj.put("success ",res+" Rows Affected");
			obj.put("status",200);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
			
		} catch (ClassNotFoundException e1) {
			obj.put("error",e1.getMessage());
			obj.put("status",500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		} catch (SQLException e1) {
			obj.put("error",e1.getMessage());
			obj.put("status",500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
		}
	}
	
	@Path("/drop/{table_name}")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response dropTable(@PathParam("table_name") String tableName, 
					@FormParam("token") String token) {
		Validate validate;
		JSONObject obj = new JSONObject();
		try {
			validate = new Validate(token);
			if (!validate.isValid()) {
				obj.put("error","Token is Invalid.");
				obj.put("status",401);
				return Response.status(Status.UNAUTHORIZED).entity(obj.toString()).build();
			}
			
			String user = validate.getUser();
			CRUD crud;
			crud = new CRUD(user, tableName);
			crud.dropTable(tableName);
			
			obj.put("success ", tableName + " Table deleted");
			obj.put("status", 200);
			
			return Response.status(Status.ACCEPTED).entity(obj.toString()).build();
		} catch (ClassNotFoundException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		} catch (SQLException e1) {
			obj.put("error", e1.getMessage());
			obj.put("status", 500);
			return Response.status(Status.BAD_REQUEST).entity(obj.toString()).build();
		}
	}
}


