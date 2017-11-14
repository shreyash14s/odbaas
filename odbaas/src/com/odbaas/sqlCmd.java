package com.odbaas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class sqlCmd 
{
	public static void main(String[] args)
	{
		connectSql();
	}
	public static void connectSql() {

	    System.out.println("----MySQL JDBC Connection Testing -------");
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("error !!!!!!");
	        e.printStackTrace();
	        return;
	    }

	    System.out.println("MySQL JDBC Driver Registered!");
	    Connection connection = null;

	    try {
	        String PUBLIC_DNS="odbaas.cfljz4glvwaq.us-east-2.rds.amazonaws.com";
			String PORT="3306";
			String DATABASE="information_schema";
			String REMOTE_DATABASE_USERNAME="sidarthur2710";
			String DATABASE_USER_PASSWORD="incorrect";
			connection = DriverManager.
	                getConnection("jdbc:mysql://" + PUBLIC_DNS + ":" + PORT + "/" + DATABASE, REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
			Statement st =  connection.createStatement();
			//st.executeUpdate("INSERT INTO mytabletest (id, name) "+"VALUES ('id001', 'Flinstone')");
			//st.executeUpdate("INSERT INTO mytabletest (id, name) "+"VALUES ('id002', 'Fred')");
			//ResultSet rs = st.executeQuery("select * from mytabletest");
			ResultSet rs = st.executeQuery("select * from GLOBAL_STATUS");
			System.out.println(ResultSetConverter.convert(rs)); 
			
			while(rs.next())
			//rs.next();
			{
				String no=rs.getString("no");
				//String name=rs.getString("name");
			//System.out.println("id:"+id+" name:"+name);
				System.out.println("no:"+no);
			}
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!:\n" + e.getMessage());
	    }

	    if (connection != null) {
	        System.out.println("SUCCESS!!!!!");
	    } else {
	        System.out.println("FAILURE! Failed to make connection!");
	    }

	}
	
}
