package tqs_ua.pt.ex1_restassured;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TODOs_test {

    @Test
    void whenGetTODO_thenCheckTitle(){  //the title of TODO_#4 is “et porro tempora”
        String expected = "et porro tempora";
        given().when().get("https://jsonplaceholder.typicode.com/todos/4").then().assertThat().statusCode(200).and().body("title", equalTo (expected))
                .and().body("id", equalTo (4));
    }

    @Test
    void whenGetAll_thenCheck198TODOs(){    //the endpoint to list all TODOs is available (status code 200) + id #198 and #199 are in the results.
        given().when().get("https://jsonplaceholder.typicode.com/todos").then().assertThat().statusCode(200).and().body("id", hasItems(198,199));
    }
}
