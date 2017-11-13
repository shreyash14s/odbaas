package com.odbaas;

import java.sql.SQLException;
import javax.ws.rs.core.Response;

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
	

}