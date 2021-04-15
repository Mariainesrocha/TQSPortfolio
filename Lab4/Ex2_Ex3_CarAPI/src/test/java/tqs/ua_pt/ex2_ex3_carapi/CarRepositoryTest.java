package tqs.ua_pt.ex2_ex3_carapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository car_repo;

    @Test
    public void whenfindByExistingCarID_thenReturnCar() {
        Car c1 = new Car("fiat", "punto");
        c1.setCarID(1234L);
        entityManager.persistAndFlush(c1);

        Car found = car_repo.findByCarID(1234L);

        assertThat(found.getModel()).isEqualTo(c1.getModel());
    }

    @Test
    public void whenfindByCarInvalidID_thenReturnNull() {
        Car c5 = car_repo.findByCarID(-12344546L);
        assertThat(c5).isNull();
    }

    @Test
    public void givenListOfCars_whenFindAll_thenReturnAllCars() {
        Car c2 = new Car("land rover", "range rover evoque");
        c2.setCarID(2222L);
        Car c3 = new Car("dacia", "sandero stepway");
        c3.setCarID(1111L);

        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.flush();

        List<Car> cars = car_repo.findAll();

        assertThat(cars).hasSize(2).extracting(Car::getMaker).containsOnly("dacia","land rover");
    }
}