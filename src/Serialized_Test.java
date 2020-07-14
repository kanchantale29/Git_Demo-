import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class Serialized_Test {
	public static void main(String[] args) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("30 side layout,India");
		ap.setLanguage("English");
		ap.setPhone_number("9876543234");
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setName("Name");
	List<String> list = new ArrayList<String>();
	list.add("shoe park");
	list.add("shop");
	ap.setTypes(list);
	Location loc = new Location();
	loc.setLat(-67.94747);
	loc.setLng(50.33455);
	ap.setLocation(loc);
	
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res =given().log().all().queryParam("key", "qaclick123")
		.body(ap)
		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
   .extract().response();	
		String responseString =res.asString();
		System.out.println(responseString);
		}

}
