package CRUDOperations.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStyle {

    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for POSTRequest with valid payload(Positive case)")
    @Test
    public void POSTReqPos() {


        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

                rs.baseUri(BASE_URI).basePath(BASE_PATH);
                rs.contentType(ContentType.JSON).log().all();
                rs.body(payload);

                res = rs.when().log().all().post();
                String restiring = res.asString();
                System.out.println(restiring);

                vr = res.then();
                vr.log().all().statusCode(200);
    }

    @Description("TC#2 - Verify 500 for POSTRequest with invalid payload(Negative case)")
    @Test
    public void POSTReqNeg() {


        String payload = "";

        rs.baseUri(BASE_URI).basePath(BASE_PATH);
        rs.contentType(ContentType.JSON).log().all();
        rs.body(payload);

        res = rs.when().log().all().post();
        String restiring = res.asString();
        System.out.println(restiring);

        vr = res.then();
        vr.log().all().statusCode(500);
    }
}

