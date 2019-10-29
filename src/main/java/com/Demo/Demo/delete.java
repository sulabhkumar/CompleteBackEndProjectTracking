package com.Demo.Demo;

import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


@Path("/delete")
public class delete {
	@DELETE
	@Path("{Project_id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	//public Response deleteItem(	String incomingData)throws Exception {
	 public Response deleteOrderById(@PathParam("Project_id") int Project_id) {
		int pk;
		int http_code=0 ;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaPostgres dao = new SchemaPostgres();	
		
		try {
			System.out.println("incomingData: "+Project_id);
//			JSONObject partsData = new JSONObject(Project_id);
//			System.out.println("partsData: "+partsData);
//			pk = partsData.optInt("Project_id", 0);
//			System.out.println("pk:"+pk);
			
			http_code = dao.deleteProject(Project_id);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
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
		



