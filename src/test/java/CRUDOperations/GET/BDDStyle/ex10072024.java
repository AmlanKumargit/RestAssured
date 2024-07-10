package CRUDOperations.GET.BDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ex10072024 {

    @Description("Verify 201 for PingRequest")
    @Test
    public void GetPingReq() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().log().all().get()
                .then().log().all().statusCode(201);
    }
}
