package com.Demo.Demo;
import java.sql.*;
import org.codehaus.jettison.json.JSONArray;


public class SchemaPostgres extends DbConnection {
	
public int updateProject(int pk, String projectName,String description,String technology,String team) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			
			conn =postgresConnection();
			query = conn.prepareStatement("UPDATE \"projectTracking\".\"ProjectList\"\n" + 
													"	SET \"projectName\" = ?, description = ?, technology = ?, team = ? \n" + 
													"	WHERE  \"Project_id\" = ? ");
			
			query.setString(1, projectName);
			query.setString(2, description);
			query.setString(3, technology);
			query.setString(4, team);
			query.setInt(5, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}


public int deleteProject(int Project_id) throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	
	try {
		
		
		conn =postgresConnection();
		query = conn.prepareStatement("DELETE FROM \"projectTracking\".\"ProjectList\"\n" + 
				"		WHERE \"Project_id\"= ? ");
		
		
		
		query.setInt(1, Project_id);
		query.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
		return 500;
	}
	finally {
		if (conn != null) conn.close();
	}
	
	return 200;
}
	
	public int insertIntoProject(String ProjectName, String description, String technology, String team)throws Exception {
		String query =null;
		Statement statement=null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
//		try {
//			conn =  postgresConnection();
//			System.out.println("Trying to insert..");
//			query = "INSERT INTO \"projectTracking\".\"ProjectList\"(description, \"projectName\", technology, team)VALUES("+description+","+ProjectName+"," +technology+","+team+")";
//			
//			System.out.println("query"+query);
//			
//			
//			statement = conn.createStatement();
//			ResultSet rs = statement.executeQuery(query);
//			
//			json = converter.toJSONArray(rs);
//			statement.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return 200;
//		}
//		finally {
//			if(conn != null)
//				conn.close();
//		}
		try {
		conn =  postgresConnection();
	query = "INSERT INTO \"projectTracking\".\"ProjectList\"(description, \"projectName\", technology, team)VALUES(?, ?, ?, ?)";
	System.out.println("query"+query);
	
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, description);
		      preparedStmt.setString (2, ProjectName);
		      preparedStmt.setString (3, technology);
		      preparedStmt.setString (4, team);
		     

		    
		      preparedStmt.execute();
		      
		      
		      conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(conn != null)
				conn.close();
		}
	return 200;
	}
	
	
	
	
	
	
	public JSONArray queryReturnPoject(String project)throws Exception{
	
	String query =null;
	Statement statement=null;
	Connection conn = null;
	
	ToJSON converter = new ToJSON();
	JSONArray json = new JSONArray();
	
	try {
		conn =  postgresConnection();
		query = "SELECT project_id, \"projectName\", description\n" + 
				"	FROM public.\"ProjectList\" + where(projectName)="+ project;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		json = converter.toJSONArray(rs);
		statement.close();
	}
	catch(Exception e){
		e.printStackTrace();
		return json;
	}
	finally {
		if(conn != null)
			conn.close();
	}
	
return json;
}
	
	public JSONArray queryReturnPojectemployee(String employee)throws Exception{
		
		String query =null;
		Statement statement=null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn =  postgresConnection();
			query = "SELECT project_id, \"projectName\", description\n" + 
					"	FROM public.\"ProjectList\" + where(employeeName) ="+employee;
			
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			json = converter.toJSONArray(rs);
			statement.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null)
				conn.close();
		}
		
	return json;
}
}