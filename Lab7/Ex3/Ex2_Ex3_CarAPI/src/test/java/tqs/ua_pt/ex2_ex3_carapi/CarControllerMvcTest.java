package tqs.ua_pt.ex2_ex3_carapi;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@WebMvcTest(CarRestController.class)
class CarControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carService;

    @BeforeEach
    void SetUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void whenGetCars_thenReturnList() {
        Car c1 = new Car("fiat", "punto"); c1.setCarID(1234l);
        Car c2 = new Car("land rover", "range rover evoque"); c2.setCarID(2222l);

        List<Car> cars = new ArrayList<>();
        cars.add(c1); cars.add(c2);

        when(carService.getAllCars()).thenReturn(cars);

        RestAssuredMockMvc.given().auth().none().when().get("/api/cars").then().statusCode(200)
                .body("$.size()", Matchers.equalTo(2))
                .body("[0].maker", Matchers.equalTo(c1.getMaker()));
    }

    @Test
    public void whenValidID_thenReturnCar() {
        Car c1 = new Car("fiat", "punto");
        c1.setCarID(1234l);

        when(carService.getCarDetails(1234l)).thenReturn(Optional.of(c1)); //qdo save é chamado no service retornamos o c1 criado em cima
        RestAssuredMockMvc.given().auth().none().when().get("/api/car/"+c1.getCarID()).then().statusCode(200)
                .body("model", Matchers.equalTo(c1.getModel()));
    }
}