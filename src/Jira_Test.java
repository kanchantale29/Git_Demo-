import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class Jira_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session = new SessionFilter();
		String response = given().log().all().header("Content-Type","Application/json").body("{\r\n" + 
				"    \"username\": \"kanchantale123\",\r\n" + 
				"    \"password\": \"Kaju@123\"\r\n" + 
				"}").log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		given().pathParam("key", "10101").log().all().header("Content-Type","Application/json").body("{\r\n" + 
				"    \"body\": \"This is my first comment\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);

	}

}
