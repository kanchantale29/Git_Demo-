import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class SpecBuilderTest {
	
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
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
	RequestSpecification  req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
	.setContentType(ContentType.JSON).build();
	
		
		
		ResponseSpecification resspec = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
		RequestSpecification res =given().spec(req)
		.body(ap);
		
	Response response =	res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();	
		String responseString =response.asString();
		System.out.println(responseString);
		}

}
