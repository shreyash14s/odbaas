package com.odbaas;

import java.sql.SQLException;
import javax.ws.rs.core.Response;

public class CRUD {
	String table;
	Database db;
	
	CRUD(String dbname) throws ClassNotFoundException, SQLException {
		// this.dbname = dbname;
		db = new Database(dbname);
	}

	void setTable(String table) {
		this.table = table;
	}
	
	void createTable( String schema,String primaryKey) throws SQLException
    {
    	String attr = "";
    	String[] temp;
    	for(String i: schema.split(","))
    	{
    		temp = i.split(":");
    		attr = attr + temp[0] + " " + temp[1] + ",";
    	}
    	String query = "CREATE TABLE " + table + " ( "  + attr + " );";
    	try {
			int res = db.update(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }
	

}