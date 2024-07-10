package CRUDOperations.GET.NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class ex10072024a {

    @Description("Verify 201 for PingRequest")
    @Test
    public void GetPingRequest() {
        RequestSpecification rs = RestAssured.given();

                rs.baseUri("https://restful-booker.herokuapp.com");
                rs.basePath("/ping");

                rs.when().log().all().get();
                rs.then().log().all().statusCode(201);

    }
}
