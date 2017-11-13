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
                } 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void doCheck()
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
        public String selectOne(String databaseName,String searchQuery,String column)
        {
            String result="";
            try
            {
                Statement st =  connection.createStatement();
                st.execute("use "+databaseName);
                ResultSet rs = st.executeQuery(searchQuery);
                if(rs.next())
                {
                    result = rs.getString(column);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return result;
        }
        public boolean isTrue(String databaseName,String searchQuery)
        {
            int  count=0;
            try
            {
                Statement st =  connection.createStatement();
                st.execute("use "+databaseName);
                ResultSet rs = st.executeQuery(searchQuery);
                while(rs.next())
                {
                    ++count;
                }                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return count > 0;            
        }
        public int insert(String databaseName,String searchQuery)
        {
            int val=0;
            try
            {
                Statement st =  connection.createStatement();
                st.execute("use "+databaseName);
                val = st.executeUpdate(searchQuery);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return val;
        }
         public int update(String databaseName,String searchQuery)
        {
            int val=0;
            try
            {
                Statement st =  connection.createStatement();
                st.execute("use "+databaseName);
                val = st.executeUpdate(searchQuery);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return val;
        }
}
