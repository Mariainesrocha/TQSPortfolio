package tqs.ua_pt.ex2_ex3_carapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {
    @Mock(lenient = true)
    private CarRepository car_repo;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    void testUp(){
        Car c1 = new Car("fiat", "punto"); c1.setCarID(1234L);
        Car c2 = new Car("land rover", "range rover evoque"); c2.setCarID(2222L);
        Car c3 = new Car("dacia", "sandero stepway"); c3.setCarID(1111L);

        List<Car> cars = Arrays.asList(c1, c2, c3);

        Mockito.when(car_repo.findByCarID(c1.getCarID())).thenReturn(c1);
        Mockito.when(car_repo.findByCarID(c2.getCarID())).thenReturn(c2);
        Mockito.when(car_repo.findByCarID(c3.getCarID())).thenReturn(c3);
        Mockito.when(car_repo.findAll()).thenReturn(cars);
        Mockito.when(car_repo.findByCarID(-124l)).thenReturn(null);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Optional<Car> c1_BD = service.getCarDetails(1234L); //Primeiro procura-se por id
        assertThat(c1_BD.isPresent()).isTrue(); //verifica se existe ou seja, se n retornou null
        Car c1 = c1_BD.get(); //dps ja podemos criar um carro c o carro q recebemos
        assertThat(c1.getMaker()).isEqualTo("fiat"); //verificar se recebemos o carro certo

        Optional<Car> c2_BD = service.getCarDetails(2222L);
        assertThat(c2_BD.isPresent()).isTrue();
        Car c2 = c2_BD.get();
        assertThat(c2.getModel()).isEqualTo("range rover evoque");

        Mockito.verify(car_repo, VerificationModeFactory.times(2)).findByCarID(Mockito.anyLong());
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        Optional<Car> car = service.getCarDetails(-1234L);
        assertThat(car).isNull();

        Mockito.verify(car_repo, VerificationModeFactory.times(1)).findByCarID(Mockito.anyLong());
    }

    @Test
    public void given3Cars_whenGetAll_thenReturn3Records() {
        List<Car> allCars = service.getAllCars();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains("fiat", "land rover", "dacia");
        Mockito.verify(car_repo, VerificationModeFactory.times(1)).findAll();
    }

}