package APIAutomation;

import java.util.*;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.restapi.propconfig.*;
import com.restapi.pojo.*;

public class UserDetails_API {
	
	@Test
	public void UserDetails_TestCase() throws Exception
	{
	 int TotalAPIPages=1,PageLimit,ActiveStudents=0,InActiveStudents=0;
	 Properties prop = PropRepo.readPropertiesFile();
	 for(int page=0;page<TotalAPIPages;page++)
	 {
	 Response APIResponse=RestAssured.given().when().get(prop.getProperty("RequestUrl")+page);
	 Gson gson = new Gson();
	 UserDetails expectedResponse = gson.fromJson(APIResponse.getBody().asString(), UserDetails.class);
	 TotalAPIPages=expectedResponse.getMeta().getPagination().getPages();
	 PageLimit=expectedResponse.getMeta().getPagination().getLimit();
	 for(int i=0;i<PageLimit;i++)
	 {
		 if(expectedResponse.getData().get(i).getEmail().equals("lld_saini_shresthi@thiel.name"))
		 {
			 String requestBody="{\n" + 
						"    \"id\": \"31\",\n" +
						"	 \"name\": \"Shresthi Saini LLD\",\n" +
						"	 \"email\": \"assignment@mpokket.com\",\n" +
						"	 \"gender\": \"Male\",\n" +
						"	 \"status\": \"Active\",\n" +
						"	 \"created_at\": \"2021-06-02T03:50:04.129+05:30\",\n" +
						"    \"updated_at\": \"2021-06-02T03:50:04.129+05:30\"\n" + "}";
				 RestAssured.baseURI = "https://gorest.co.in/public-api/users/31";
				 RestAssured.given() .header("Content-type", "application/json").and() .body(requestBody) .when().put().then().extract().response();
				 System.out.println("Updated Email");
			 
		 }
		 if(expectedResponse.getData().get(i).getStatus().equals("Active"))
		     ActiveStudents++;
		 if(expectedResponse.getData().get(i).getStatus().equals("Inactive"))
			 InActiveStudents++;
	 }
	 }
	 System.out.println("Active Students:"+ActiveStudents);
	 System.out.println("Inactive Students"+InActiveStudents);
	}
 }
