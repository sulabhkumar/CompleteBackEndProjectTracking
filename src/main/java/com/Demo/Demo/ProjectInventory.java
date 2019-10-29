package com.Demo.Demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Path("/inventory")
public class ProjectInventory {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllProjects() throws Exception{
		String query = null;
		Statement statement =null;
		Connection conn =null;
		String returnString=null;
		Response rp = null;
		
		try {
			conn = DbConnection.getconnection();
			query = "SELECT \"Project_id\", description, \"projectName\", technology, team\r\n" + 
					"	FROM \"projectTracking\".\"ProjectList\"; ";
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
