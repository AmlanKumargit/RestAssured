package CRUDOperations.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonBDD {

    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";
    String token = "578a1dcdcb58fe2";
    String bookingid = "3077";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 201 for DELPUTRequest(Positive case)")
    @Test
    public void DELReq() {

        String BASE_PATH_updated = BASE_PATH+"/"+bookingid;

        rs.baseUri(BASE_URI).basePath(BASE_PATH_updated);
        rs.contentType(ContentType.JSON);
        rs.cookie("token", token).log().all();

        res = rs.when().log().all().delete();


        vr = res.then().log().all();
        vr.statusCode(201);
    }
}
