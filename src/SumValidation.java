import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {


    @Test
    public void sumOfCourse() {
        JsonPath js = new JsonPath(Payload.coursePrice());
        int count = js.get("courses.size()");
        int total = js.getInt("dashboard.purchaseAmount");
        int totalSum = 0;
        for (int i = 0; i < count; i++) {
            int coursePrice = js.getInt("courses[" + i + "].price");//50
            int courseCopies = js.getInt("courses[" + i + "].copies");//6
            int sum = coursePrice * courseCopies;//300
            totalSum=totalSum+sum;
        }
        System.out.println(totalSum);//900

        Assert.assertEquals(totalSum, total);
    }

}
