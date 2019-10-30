package com.Demo.Demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;





@Path("/update")
public class update {
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateItem(@PathParam("update") String update,
//									String incomingData) 
//								throws Exception {
		
		public Response updateItem(String incomingData)throws Exception {
		
		int pk;
		String projectName;
		String description;
		String technology;
		String team;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaPostgres dao = new SchemaPostgres();

		try {
			System.out.println("Incoming Data"+ incomingData);
			JSONObject projectData = new JSONObject(incomingData); 
			pk = projectData.optInt("Project_id", 0);
			projectName = projectData.optString("projectName", " ");
			description = projectData.optString("description", " ");
			technology = projectData.optString("technology", " ");
			team = projectData.optString("team", " ");
			
			
			
			http_code = dao.updateProject(pk, projectName, description, technology, team);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
