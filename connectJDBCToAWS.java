package mysqlConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectJDBCToAWS {
	/*public static void main(String args[])
	{
		connectSql();
	}*/
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
			String DATABASE="mytest";
			String REMOTE_DATABASE_USERNAME="sidarthur2710";
			String DATABASE_USER_PASSWORD="incorrect";
			connection = DriverManager.
	                getConnection("jdbc:mysql://" + PUBLIC_DNS + ":" + PORT + "/" + DATABASE, REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
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
