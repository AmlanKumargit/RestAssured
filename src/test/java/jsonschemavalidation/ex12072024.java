package jsonschemavalidation;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

public class ex12072024 {

    @Test
    public void JSONvalidation(){

        RestAssured.given().baseUri("https://jsonplaceholder.typicode.com/posts/1").
                when().get().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schema.json")));
    }
}
