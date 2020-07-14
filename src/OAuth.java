import static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import POJO.Api;
import POJO.GetCourse;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class OAuth {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
		 * WebDriver driver = new ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss"
		 * ); driver.findElement(By.cssSelector("input[type='email']")).sendKeys(
		 * "kanchantale123@gmail.com");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER
		 * ); Thread.sleep(3000);
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * "Live@thelife29");
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		 * ENTER); Thread.sleep(4000);
		 */
		//String url=driver.getCurrentUrl();
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F1gGtJcxzXi3YlDj_rWNK2OfuPd9oUSI0AJmYJWhoRCieG4DTdVwCBcjT4SKsI58M0G9tUYGhDscx_RyOpU9AMyg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);





		//RestAssured.baseURI = "";
		String accessTokenResponse = given().queryParams("code",code).log().all()
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_url","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken=js.getString("access_token");
		/*
		 * String response = given().queryParam("access_token", accessToken)
		 * .when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
		 * .asString(); //System.out.println(response);
		 */		
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		System.out.println(gc.getLinkedIN());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		List<Api> ApiCourses =gc.getCourses().getApi();
		for(int i=0;i<ApiCourses.size();i++) {
			if(ApiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webservices testing")) {
System.out.println(ApiCourses.get(i).getPrice());
				
			}
		}

	}

}
