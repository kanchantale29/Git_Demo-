import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		
	
	JsonPath js2 = new JsonPath(PayLoad.coursePrice());
	//print number of courses
	int count =js2.getInt("courses.size()");
	System.out.println(count);
	//print purchase amount
	int totalamount = js2.getInt("dashboard.purchaseAmount");
	System.out.println(totalamount);
	//print title of the courses
	
String title = js2.get("courses[0].title");
System.out.println(title);
//print all course and their respective price
for(int i=0;i<count ;i++) {
	String courseTitle =js2.get("courses["+i+"].title");
	int price = js2.getInt("courses["+i+"].price");
	System.out.println(courseTitle);
	System.out.println(price);
	
}
//print number of copies sold by RPA course
for(int i=0;i<count;i++) {
	String courseTitle =js2.get("courses["+i+"].title");
	if(courseTitle.equalsIgnoreCase("RPA")) {
		 int copies = js2.get("courses["+i+"].copies");
		 System.out.println(copies);
		 break;
	}
}

}
}
