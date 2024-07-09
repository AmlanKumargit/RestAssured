package com.amlankumar.ra02;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ex09072024 {

    @Description("Verify 201 for PingRequest1")
    @Test
    public void GetPingRequest1() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().get()
                .then().statusCode(201);
        System.out.println("TC1");
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
        System.out.println("TC2");
    }

    @Description("Verify 201 for PingRequest3")
    @Test
    public void GetPingRequest3() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().get()
                .then().statusCode(201);
        System.out.println("TC3");
    }
}
