package com.Demo.Demo;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class DbConnection {

	public static Connection getconnection() {
		System.out.println("Trying to connect....");
		Connection connection = null;
		try {
			 Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "sulabh6065");
			System.out.println(connection);
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}
		public static Connection postgresConnection() {
			Connection conn =null;
			try {
				conn = DbConnection.getconnection();
				return conn;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
}
