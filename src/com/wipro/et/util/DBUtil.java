package com.wipro.et.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getDBConnection()
	{
		String dbUrl= "jdbc:oracle:thin:@localhost:1521:xe";
		String dbname="devops";
		String dbpwd="accounttracker";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(dbUrl, dbname,dbpwd);
			
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
}
