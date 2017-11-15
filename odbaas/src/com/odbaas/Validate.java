package com.odbaas;

import java.sql.SQLException;

public class Validate extends Login {
	Validate(String token) throws ClassNotFoundException, SQLException {
		super(token);
	}

	public boolean isValid() 
	{
		
		if (this.getUserName().length() == 0) {
			return false;
		}
		return this.isTokenValid();
	}

	public String getUser() {
		return this.getUserName();
	}
}
