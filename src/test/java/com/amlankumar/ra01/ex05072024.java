package com.amlankumar.ra01;

import io.restassured.RestAssured;

public class ex05072024 {
    public static void main(String[] args) {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/ping")

                .when().get()
                .then().statusCode(201);
        System.out.println("GET COMPLETED");
    }
}
