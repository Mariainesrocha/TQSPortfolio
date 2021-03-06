package tqs_ua.pt.ex1_restassured;

import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class My1stRESTAssuredClass {

    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    public static void main(String args[]) {

        getResponseBody();
        getResponseStatus();

        ; }

    //This will fetch the response body as is and log it. given and when are optional here
    public static void getResponseBody(){
        given().when().get(url).then().log()
                .all();

        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
    }

    public static void getResponseStatus(){
        int statusCode= given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
        System.out.println("The response status is "+statusCode);

        given().when().get(url).then().assertThat().statusCode(200);
    }

    public static void getResponseHeaders(){
        System.out.println("The headers in the response "+
                get(url).then().extract()
                        .headers());
    }

    public static void getResponseTime(){
        System.out.println("The time taken to fetch the response "+get(url)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    }

}