package com.odbaas;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CRUD {
	String table;
	Database db;
	
	CRUD(String dbname,String table) throws ClassNotFoundException, SQLException {
		db = new Database(dbname);
		this.table = table;
	}
	
	void setTable(String table) {
		this.table = table;
	}
	
	void createTable(String schema, String primaryKey) throws SQLException {
		String attr = "";
		String[] temp = schema.split(",");
		for (String i : temp) {
			temp = i.split(":");
			attr = attr + temp[0] + " " + temp[1] + ",";
		}
		if (temp.length > 0) {
			attr = attr.substring(0,attr.length()-1);
		}
		
		if (primaryKey != null) {
			attr = attr + ", PRIMARY KEY (" + primaryKey + ")";
		}
		
		String query = "CREATE TABLE " + table + " ( "  + attr + " );";
		// System.out.println("my create query: " + query);
		try {
			int res = db.update(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	JSONArray selectTable(String columns, String where, String sortColumns, 
	String sortOrder, String limit) throws SQLException 
	{
		if (columns == null ) {
                        
			columns = "*";
		}
		
		String query = "SELECT " + columns + " FROM " + this.table;
		
		if (where != null) {
			query = query + " WHERE " + where;
		}
		
		if (sortColumns != null) {
			query = query + " ORDER BY " + sortColumns;
			if(sortOrder == null) {
				query = query + " ASC ";
			} else {
				query = query + " " + sortOrder;
			}
		}
		
		if (limit != null)
		query = query + " LIMIT " + limit;
		
		query = query + ";";
		
		System.out.println(query);
		try {
			JSONArray a = db.selectAllRow(query);
			// System.out.println(a.toString());
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	private List<String> getKeys(JSONArray array) {
		JSONObject obj = array.getJSONObject(0);
		Iterator<String> it = obj.keys();
		List<String> keys = new ArrayList<String>();
		while(it.hasNext()) {
			keys.add((String)it.next());
		}
		return keys;
	}
	
	int insertTable(String data) throws SQLException, JSONException {
		try {
			JSONArray array = new JSONArray(data);
			if (array.length() == 0) {
				throw new JSONException("Empty JSON array");
			}
			List<String> keys = getKeys(array);
			String fields = String.join(",", keys);
			fields = "( " + fields + " )";
			
			String[] place = new String[keys.size()];
			for(int i = 0; i < place.length; ++i) {
				place[i] = "?";
			}
			
			String compiled = "(" + String.join(",", place) + ")";
			String query = "INSERT INTO " + this.table + " " + fields + " VALUES" + compiled;
			// System.out.println("Query is: " + query);
			int i = db.insert(query, keys, array);
			// System.out.println("Total is : " + i);
			return i;
		} catch(JSONException e) {
			e.printStackTrace();
			throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	int updateTable(String tableName, String set, String where) throws SQLException, JSONException {
		String query = "UPDATE " + tableName + " SET " + set;
		
		if (where != null) {
			query = query + " WHERE " + where;
		}
		
		query = query + ";";
		
		try {
			int a = db.update(query);
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	int deleteTable(String tableName, String where) throws SQLException {
		String query = "DELETE FROM " + tableName + " where "  + where + ";";
		System.out.println("my delete query: " + query);
		try {
			int res = db.update(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	void dropTable(String tableName) throws SQLException {
		String query = "DROP TABLE " + tableName + ";";
		System.out.println("my delete query: " + query);
		try {
			int res = db.update(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
