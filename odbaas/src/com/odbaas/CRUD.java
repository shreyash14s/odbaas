package com.odbaas;

import java.sql.SQLException;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import jersey.repackaged.com.google.common.collect.SortedMapDifference;


public class CRUD {
	String table;
	Database db;
	
	CRUD(String dbname,String table) throws ClassNotFoundException, SQLException {
		// this.dbname = dbname;
		db = new Database(dbname);
		this.table=table;
	}

	void setTable(String table) {
		this.table = table;
	}
	
	void createTable( String schema,String primaryKey) throws SQLException
    {
    	String attr = "";
    	String[] temp=schema.split(",");
    	for(String i: temp)
    	{
    		temp = i.split(":");
    		attr = attr + temp[0] + " " + temp[1] + ",";
    	}
    	if(temp.length>0)
    	{
    		attr = attr.substring(0,attr.length()-1);
    	}
    	String query = "CREATE TABLE " + table + " ( "  + attr + " );";
    	System.out.println("my create query: "+query);
    	try {
			int res = db.update(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }
	
	JSONArray selectTable(String  columns,String where,String sortColumns,String sortOrder,String limit) throws SQLException
    {
    	System.out.println("Hello");
		String query = "SELECT " + columns + " FROM " + this.table;
		if(where != null)
			query = query + " WHERE " + where;
		if(sortColumns != null)
		{
			query = query + " ORDER BY " + sortColumns;
			if(sortOrder == null)
				query = query + " ASC ";
			else
				query = query + " " + sortOrder;
		}
		
		if(limit != null)
			query = query + " LIMIT " + limit;
		
		query = query + ";";
		
		System.out.println(query);
    	try {
    		JSONArray a = db.selectAllRow(query);
    		System.out.println(a.toString());
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }

}