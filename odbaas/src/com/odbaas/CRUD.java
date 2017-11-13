package com.odbaas;

import java.sql.SQLException;

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
}
