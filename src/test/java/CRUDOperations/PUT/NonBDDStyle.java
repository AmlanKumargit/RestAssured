package CRUDOperations.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonBDDStyle {

    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";
    String token = "b296b4422638b8d";
    String bookingid = "40695";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for PUTRequest with valid payload(Positive case)")
    @Test
    public void PUTReq() {

        String BASE_PATH_updated = BASE_PATH+"/"+bookingid;
        String payload = "{\n" +
                "    \"firstname\" : \"Amlan\",\n" +
                "    \"lastname\" : \"Kumar\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        rs.baseUri(BASE_URI).basePath(BASE_PATH_updated);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
        rs.body(payload).log().all();;

        res = rs.when().log().all().put();


        vr = res.then().log().all();
        vr.statusCode(200);
        vr.body("firstname", Matchers.equalTo("Amlan"));
        vr.body("lastname", Matchers.equalTo("Kumar"));
    }
}
