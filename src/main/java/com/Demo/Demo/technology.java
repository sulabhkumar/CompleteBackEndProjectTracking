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

@Path("/tech")
public class technology {
	@GET
	@Path("{technology}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchProject(@PathParam("technology") String technology) throws Exception{
		String query = null;
		Statement statement =null;
		Connection conn =null;
		String returnString=null;
		Response rp = null;
		
		try {
			System.out.println("IN Technology");
			System.out.println("incomingData: "+technology);
			conn = DbConnection.getconnection();
			query = "SELECT \"Project_id\", description, \"projectName\", technology, team\r\n" + 
					"	FROM \"projectTracking\".\"ProjectList\" where technology ILIKE"+ "'%"+technology+"%'";
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
