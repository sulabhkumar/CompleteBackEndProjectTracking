package com.Demo.Demo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;



@Path("/insert")
public class InsertProject{
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
public Response addProject(String incomingData) throws Exception {
		
		String returnString = null;
		String json =null;
		
		SchemaPostgres dao = new SchemaPostgres();
		
		try {
			System.out.println("incomingData: " + incomingData);
			
			ObjectMapper mapper = new ObjectMapper();  
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoProject(itemEntry.projectName, 
													itemEntry.description, 
													itemEntry.technology, 
													itemEntry.team
													 );
			System.out.println("http_code: "+http_code);
			
			if( http_code == 200 ) {
				
				returnString = "Item inserted";
				Gson gson = new Gson();
				 json = gson.toJson(returnString); 
				
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(json).build();
	}
	
}
class ItemEntry {
	public String projectName;
	public String description;
	public String technology;
	public String team;
	
}
