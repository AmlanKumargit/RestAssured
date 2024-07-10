package Full_CRUD_E2E;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Full_CRUD {

    String BASE_URI = "https://restful-booker.herokuapp.com";
    String token;
    Integer bookingid;
    RequestSpecification rs = RestAssured.given();
    Response res;
    ValidatableResponse vr;

    @BeforeTest
    public void AGetToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        rs.baseUri(BASE_URI);
        rs.basePath("/auth");
        rs.contentType(ContentType.JSON);
        rs.body(payload);

        res = rs.when().log().all().post();

        vr = res.then();
        vr.statusCode(200);
        token = vr.log().all().extract().path("token");
        Assert.assertNotNull(token);

    }
    @BeforeTest
    public void CreateBookingID(){
        String payload = "{\n" +
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

        rs.baseUri(BASE_URI);
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON);
        rs.body(payload);

        res = rs.when().log().all().post();

        vr = res.then();
        vr.statusCode(200);
        vr.body("booking.firstname", Matchers.equalTo("Jim"));
        vr.body("booking.lastname", Matchers.equalTo("Brown"));
        bookingid = vr.log().all().extract().path("bookingid");
        Assert.assertNotNull(bookingid);
        System.out.println("Booking ID:" + bookingid);
    }

    @Test(priority = 1)
    public void UpdateBookingID(){
        String payload = "{\n" +
                "    \"firstname\" : \"Amlan\",\n" +
                "    \"lastname\" : \"Kumar\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        rs.baseUri(BASE_URI).basePath("/booking/"+bookingid);
        rs.contentType(ContentType.JSON);
        rs.cookie("token", token);
        rs.body(payload).log().all();;

        res = rs.when().log().all().put();


        vr = res.then().log().all();
        vr.statusCode(200);
        vr.body("firstname", Matchers.equalTo("Amlan"));
        vr.body("lastname", Matchers.equalTo("Kumar"));
    }

    @Test(priority = 2)
    public void GetBookingInfo(){

        rs.baseUri(BASE_URI);
        rs.basePath("/booking/"+bookingid);
        rs.contentType(ContentType.JSON);

        res = rs.when().log().all().get();
        vr = res.then().log().all().statusCode(200);
        vr.body("firstname", Matchers.equalTo("Amlan"));
        vr.body("lastname", Matchers.equalTo("Kumar"));
    }

    @Test(priority = 3)
    public void DeleteBookingID(){
        rs.baseUri(BASE_URI);
        rs.basePath("/booking/"+bookingid);
        rs.contentType(ContentType.JSON);
        rs.cookie("token", token).log().all();

        res = rs.when().log().all().delete();
        String restring = res.asString();
        System.out.println(restring);
        Assert.assertEquals(restring, "Created");

        vr = res.then().log().all();
        vr.statusCode(201);
    }

}
