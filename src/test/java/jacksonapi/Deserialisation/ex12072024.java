package jacksonapi.Deserialisation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import gson.serialization.Booking;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ex12072024 {
    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";

    RequestSpecification rs = RestAssured.given();
    ;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for POSTRequest with valid payload(Positive case)")
    @Test
    public void POSTReqPos() throws JsonProcessingException {

        String JSONpayload = "{\n" +
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

        ObjectMapper om = new ObjectMapper();
        Booking booking = om.readValue(JSONpayload, Booking.class);
        System.out.println(booking.toString());
        System.out.println(booking.getBookingdates());

        Assert.assertEquals(booking.getFirstname(),"Jim");
    }
}
