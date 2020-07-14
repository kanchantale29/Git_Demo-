

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.PayLoad;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response =	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json;charset=UTF-8").body(PayLoad.addPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
System.out.println(response);
JsonPath js = new JsonPath(response);//for parsing json
String placeID = js.getString("place_id");
System.out.println(placeID);

//update place
String newAddress ="winter walk, USA";
given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json;charset=UTF-8")
.body("{\r\n" + 
		"\"place_id\":\""+placeID+"\",\r\n" + 
		"\"address\":\""+newAddress+"\",\r\n" + 
		"\"key\":\"qaclick123\"\r\n" + 
		"}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
//get place
String getPlaceResponse = given().log().all().queryParam("place_id", placeID).queryParam("key", "qaclick123").when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1 = new JsonPath(getPlaceResponse);
	String add =js1.getString("address");
	//System.out.println(add);
	Assert.assertEquals(add, newAddress );
	}
	

}

