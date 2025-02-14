package com.amlankumar.ra01;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ex05072024 {

    @Description("Verify 201 for PingRequest")
    @Test
    public void GetPingRequest() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().get()
                .then().statusCode(201);
        System.out.println("PING1");
    }

    @Description("Verify 201 for PingRequest2")
    @Test
    public void GetPingRequest2() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().get()
                .then().statusCode(201);
        System.out.println("PING2");
    }
}
