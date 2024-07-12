package jacksonapi.serialisation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gson.serialization.Booking;
import gson.serialization.BookingDates;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class ex12072024 {
    String BASE_URI = "https://restful-booker.herokuapp.com";
    String BASE_PATH = "/booking";

    RequestSpecification rs = RestAssured.given();;
    Response res;
    ValidatableResponse vr;

    @Description("TC#1 - Verify 200 for POSTRequest with valid payload(Positive case)")
    @Test
    public void POSTReqPos() throws JsonProcessingException {

        /*String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";*/

        Faker faker = new Faker(); // to generate random name, number data.
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        Booking booking = new Booking();
        booking.setFirstname(firstname);
        booking.setLastname(lastname);
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bd = new BookingDates();
        bd.setCheckin("2018-01-01");
        bd.setCheckout("2019-01-01");
        booking.setBookingdates(bd);
        booking.setAdditionalneeds("Breakfast");


        System.out.println("Payload:" + "\n" + booking.toString());

        ObjectMapper om = new ObjectMapper();
        String JSONString = om.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        System.out.println(JSONString);


    }
}
