import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//Validate add place APi are working as not
		//Give Input of all details
		//When hit the API
		//Then validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

		System.out.println(response);

		JsonPath js=new JsonPath(response); //For Parsing Json ,JsonPath is class
		String placeId=js.getString("place_id");
		System.out.println(placeId);


		//Update Place
		String expectedaddr="#345 MG Road";
		given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
				.body("{\n" +
						"\"place_id\":\""+placeId+"\",\n" +
						"\"address\":\""+expectedaddr+"\",\n" +
						"\"key\":\"qaclick123\"\n" +
						"}").when().put("maps/api/place/update/json").
				then().statusCode(200).body("msg",equalTo("Address successfully updated"));


		//Get Place
		given().log().all().queryParam("Key","qaclick123")
						.queryParam("place_id",placeId)
				.when()
				.get("maps/api/place/get/json")
				.then()
				.statusCode(200).
				extract()
				.response()
				.asString();
//		JsonPath js1=ReUsableMethods.rawToJson();
//		String addr=js1.getString("address");
//		Assert.assertEquals(addr,expectedaddr);

	}

}
