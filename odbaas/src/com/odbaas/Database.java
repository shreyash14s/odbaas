package com.odbaas;
import java.sql.BatchUpdateException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Database 
{
	Connection connection=null;
	private final  String myConnector = "com.mysql.jdbc.Driver";
	private final String PUBLIC_DNS = "odbaas.cfljz4glvwaq.us-east-2.rds.amazonaws.com";
	private final String PORT = "3306";
	private final String DATABASE = "mytest";
	private final String REMOTE_DATABASE_USERNAME = "sidarthur2710";
	private final String DATABASE_USER_PASSWORD = "incorrect";
	
	public Database(String database) throws ClassNotFoundException, SQLException {
		doCheck();
		String url = "jdbc:mysql://" + PUBLIC_DNS + ":" + PORT;
		
		try {
			connection = DriverManager.getConnection(url, 
						REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
			connection.createStatement().execute("use " + database);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void doCheck() throws ClassNotFoundException {
		try  {
			Class.forName(myConnector);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String selectOne(String searchQuery, String column) throws SQLException {
		String result = "";
		try {
			Statement st =  connection.createStatement();
			// st.execute("use "+databaseName);
			ResultSet rs = st.executeQuery(searchQuery);
			if(rs.next()) {
				result = rs.getString(column);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public String[] selectOneRow(String searchQuery) throws SQLException {
		try {
			Statement st =  connection.createStatement();
			// st.execute("use "+databaseName);
			ResultSet rs = st.executeQuery(searchQuery);
			String[] result = null;
			if(rs.next()) {
				result = new String[rs.getMetaData().getColumnCount()];
				for (int i = 0; i < result.length; i++) {
					result[i] = rs.getString(i);
				}
			}
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public JSONArray selectAllRow(String query) throws SQLException {
		try {
			Statement st =  connection.createStatement();
			// st.execute("use "+databaseName);
			ResultSet rs = st.executeQuery(query);
			return ResultSetConverter.convert(rs);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean isTrue(String searchQuery) throws SQLException {
		int count = 0;
		try {
			Statement st = connection.createStatement();
			// st.execute("use "+databaseName);
			ResultSet rs = st.executeQuery(searchQuery);
			while (rs.next()) {
				++count;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return count > 0;
	}

	public int insert(String searchQuery) throws SQLException {
		int val = 0;
		try {
			Statement st =  connection.createStatement();
			// st.execute("use "+databaseName);
			val = st.executeUpdate(searchQuery);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return val;
	}

	public int update(String searchQuery) throws SQLException {
		int val = 0;
		try {
			Statement st =  connection.createStatement();
			// st.execute("use "+databaseName);
			val = st.executeUpdate(searchQuery);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return val;
	}
	public int insert(String query,List<String> keys,JSONArray array) throws SQLException,JSONException,BatchUpdateException
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(query); 
            connection.setAutoCommit(false);
            for(int i=0;i<array.length();++i)
            {
                JSONObject obj = array.getJSONObject(i);
                System.out.println("For row number :"+i);
                for(int j=0;j<keys.size();++j)
                {
                    System.out.println("Key is: "+keys.get(j));
                    Object arg = obj.get(keys.get(j));
                    
                    if (arg instanceof Integer)
                    {
                        System.out.println("Values is:"+(Integer)arg);
                        ps.setInt((j+1), (Integer) arg);
                    }
                    else if(arg instanceof Long)
                    {
                        ps.setLong((j+1), (Long) arg);
                    }
                    else if(arg instanceof Double)
                    {
                        ps.setDouble((j+1), (Double) arg);
                    }
                    else if(arg instanceof Float)
                    {
                        ps.setFloat((j+1), (Float) arg);
                    }
                    else
                    {
                        System.out.println("Values is:"+(String)arg);
                        ps.setString((j+1),(String) arg);
                    }
                }
                ps.addBatch();
            }
            int[] all=ps.executeBatch();
            connection.commit();
            int count=0;
            for(int k=0;k<all.length;++k)
            {
                count+=all[k];
            }
            return count;
        }
        catch(SQLException e)
        {
            if(connection!=null)
            {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            throw e;
        }           
    }

}
