package com.odbaas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	Connection connection=null;
	private final  String myConnector = "com.mysql.jdbc.Driver";
	private final String PUBLIC_DNS="odbaas.cfljz4glvwaq.us-east-2.rds.amazonaws.com";
	private final String PORT="3306";
	private final String DATABASE="mytest";
	private final String REMOTE_DATABASE_USERNAME="sidarthur2710";
	private final String DATABASE_USER_PASSWORD="incorrect";
	public Database() 
	{
		doCheck();
		String url="jdbc:mysql://" + PUBLIC_DNS + 
				":" + PORT + "/" + DATABASE, REMOTE_DATABASE_USERNAME, 
				DATABASE_USER_PASSWORD);
		connection=DriverManager.getConnection(url);
	}
	void doCheck()
	{
		try 
		{
			Class.forName(myConnector);
	    } 
		catch (ClassNotFoundException e) 
		{
	        System.out.println("error !!!!!!");
	        e.printStackTrace();
		}
	}
}
