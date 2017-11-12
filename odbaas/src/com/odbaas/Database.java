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
				":" + PORT ;
		
		try 
		{
			connection=DriverManager.getConnection(url, REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
                        Statement st =  connection.createStatement();
                        st.execute("use "+DATABASE);
                        ResultSet rs = st.executeQuery("select * from mytabletest");
                        while(rs.next())
			//rs.next();
			{
				String id=rs.getString("id");
                                String name=rs.getString("name");
				//String name=rs.getString("name");
			//System.out.println("id:"+id+" name:"+name);
				System.out.println("id:"+id);
                                System.out.println("name:"+name);
			}
			System.out.println("All Good ! :) :)");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
