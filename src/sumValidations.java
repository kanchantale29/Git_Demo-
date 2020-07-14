import org.testng.Assert;
import org.testng.annotations.Test;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class sumValidations {
	@Test
	public void sumofCourses() {
		int sum=0;
		JsonPath js2 = new JsonPath(PayLoad.coursePrice());
		int count =js2.getInt("courses.size()");
		for(int i=0;i<count;i++) {
			int price =js2.get("courses["+i+"].price");
			int copies =js2.get("courses["+i+"].copies");
			int amount = price * copies; 
			System.out.println(amount);
			sum= sum+amount;
		}
			System.out.println(sum);
			int purchaseAmount = js2.getInt("dashboard.purchaseAmount");
			//System.out.println(purchaseAmount);
			Assert.assertEquals(sum, purchaseAmount);
		
				
			}
			
		}
		
		
		
	

