package CRUDOperations.POST;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PayLoadAsMap {

    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for POSTRequest with valid payload(Positive case)")
    @Test
    public void POSTReqPos() {

        /*String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";*/

        Map<String, Object> PayloadMap = new LinkedHashMap<>();
        // Linked Hash Map as it maintains the order of payload data inserted
        // HashMap wouldn't maintain order and TreeMap would sort the data.
        Faker faker = new Faker(); // to generate random name, number data.
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        PayloadMap.put("firstname",firstname);
        PayloadMap.put("lastname",lastname);
        PayloadMap.put("depositpaid",faker.random().nextInt(1000));
        PayloadMap.put("totalprice",faker.random().nextBoolean());

        Map<String, Object> bookingdatesMap = new LinkedHashMap<>();

        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        PayloadMap.put("bookingdates",bookingdatesMap);
        PayloadMap.put("additionalneeds","BreakFast");

        System.out.println("Payload:" + "\n" + PayloadMap);


        rs.baseUri(BASE_URI).basePath(BASE_PATH);
        rs.contentType(ContentType.JSON).log().all();
        rs.body(PayloadMap);

        res = rs.when().log().all().post();
        String restiring = res.asString();
        System.out.println(restiring);

        vr = res.then();
        vr.log().all().statusCode(200);
    }
}

