package CRUDOperations.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NONBDDStyle {
    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";
    String token = "ae2c2e70c3eea6d";
    String bookingid = "5111";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for PATCHRequest with valid payload(Positive case)")
    @Test
    public void PATCHReq() {

        String BASE_PATH_updated = BASE_PATH+"/"+bookingid;
        String payload = "{\n" +
                "    \"firstname\" : \"Patched\",\n" +
                "    \"lastname\" : \"Name\"\n" +
                "}";

        rs.baseUri(BASE_URI).basePath(BASE_PATH_updated);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
        rs.body(payload).log().all();;

        res = rs.when().log().all().patch();


        vr = res.then().log().all();
        vr.statusCode(200);
        vr.body("firstname", Matchers.equalTo("Patched"));
        vr.body("lastname", Matchers.equalTo("Name"));
    }
}
