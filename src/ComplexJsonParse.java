import files.Payload;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());
        //Print Number of Course returned by API
        int count = js.getInt("courses.size()");
        System.out.println("Total number of course is : " + count);
        //Print Total Price of the course
        int total = js.getInt("dashboard.purchaseAmount");
        System.out.println("Total course price : " + total);
        //Print Title of First Course
        String titleFirstCourse = js.getString("courses[0].title");
        System.out.println("Title of first course is : " + titleFirstCourse);
        //Print the Price of Selenium Course
        int seleniumPrice = js.getInt("courses[0].price");
        System.out.println("Selenium course price : " + seleniumPrice);
        //Print the Price of Cypress Course
        int cypressPrice = js.getInt("courses[1].price");
        System.out.println("Cypress course price : " + cypressPrice);
        //Print the Price of RPA Course
        int rpaPrice = js.getInt("courses[2].price");
        System.out.println("RPA course price : " + rpaPrice);

        for (int i = 0; i < count; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            int coursePrice = js.getInt("courses[" + i + "].price");
            int courseCopies = js.getInt("courses[" + i + "].copies");
            System.out.println(courseTitle + " : " + coursePrice + " :  " + courseCopies);

        }

        //Print number of Copies sold by RPA Course

        for (int i = 0; i < count; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copiesCount = js.get("courses[" + i + "].copies");
                System.out.println(copiesCount);
            }
            int totalSum = 0;
            for (int j = 0; j < count; j++) {
                int coursePrice = js.getInt("courses[" + j + "].price");//50
                int courseCopies = js.getInt("courses[" + j + "].copies");//6
                int sum = coursePrice * courseCopies;//300
                totalSum = totalSum + sum;//0+300
            }
            System.out.println(totalSum);//900
            Assert.assertEquals(totalSum, total);


        }


    }

}
