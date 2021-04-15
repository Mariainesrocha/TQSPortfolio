package tqs.ua_pt.ex2_ex3_carapi;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarControllerTestIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository car_repo;

    @AfterEach
    public void resetDB() {
        car_repo.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car c1 = new Car("fiat", "punto");
        ResponseEntity<Car> resp = restTemplate.postForEntity("/api/cars", c1, Car.class);

        List<Car> found = car_repo.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("fiat");
    }


    @Test
    public void givenCars_whenGetAllCars_thenReturnCars()  {
        Car c1 = new Car("fiat", "punto"); c1.setCarID(1234l);
        Car c3 = new Car("dacia", "sandero stepway"); c3.setCarID(1111l);

        car_repo.saveAndFlush(c1);
        car_repo.saveAndFlush(c3);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("fiat", "dacia");

    }

}