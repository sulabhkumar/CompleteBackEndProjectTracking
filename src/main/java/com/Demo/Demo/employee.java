package com.Demo.Demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

@Path("/employee")
public class employee {
	@GET
	@Path("{employee}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchProject(@PathParam("employee") String employee) throws Exception{
		String query = null;
		Statement statement =null;
		Connection conn =null;
		String returnString=null;
		Response rp = null;
		
		try {
			System.out.println("incomingData: "+employee);
			conn = DbConnection.getconnection();
			query = "SELECT  name, address, email\r\n" + 
					"	FROM \"projectTracking\".\"EmployeeDeatails\" where teamname LIKE"+ "'"+employee+"'";
			System.out.println("Query: "+query);
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			ToJSON converter =new ToJSON();
			JSONArray json = new JSONArray();
			json= converter.toJSONArray(rs);
			statement.close();
			
			
			returnString = json.toString();
			rp = Response.ok(returnString).build();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		return rp;
	}

}
