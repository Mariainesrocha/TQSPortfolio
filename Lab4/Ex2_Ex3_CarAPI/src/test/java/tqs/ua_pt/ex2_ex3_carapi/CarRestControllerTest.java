package tqs.ua_pt.ex2_ex3_carapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarRestController.class)
class CarRestControllerTest {
    @Autowired
    private MockMvc testServlet;

    @MockBean
    private CarManagerService carService;

    @Test
    void whenPostCar_thenReturnCreatedCar() throws Exception {
        Car c1 = new Car("fiat", "punto");

        when(carService.save(Mockito.any())).thenReturn(c1); //qdo save é chamado no service retornamos o c1 criado em cima

        testServlet.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(c1))) //simular o post
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("fiat")));

        verify(carService, times(1)).save(Mockito.any()); //confirmar q o metodo save foi chamado 1x
    }

    @Test
    public void whenGetCars_thenReturnList() throws Exception {
        Car c1 = new Car("fiat", "punto"); c1.setCarID(1234l);
        Car c2 = new Car("land rover", "range rover evoque"); c2.setCarID(2222l);

        List<Car> cars = new ArrayList<>();
        cars.add(c1); cars.add(c2);

        when(carService.getAllCars()).thenReturn(cars);

        testServlet.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].maker", is("fiat")))
                .andExpect(jsonPath("$[0].model", is("punto")))
                .andExpect(jsonPath("$[1].maker", is("land rover")));

        verify(carService, times(1)).getAllCars(); //confirmar q o metodo foi chamado 1x
    }

    @Test
    public void whenValidID_thenReturnCar() throws Exception {
        Car c1 = new Car("fiat", "punto");
        c1.setCarID(1234l);

        when(carService.getCarDetails(1234l)).thenReturn(Optional.of(c1)); //qdo save é chamado no service retornamos o c1 criado em cima

        testServlet.perform(get("/api/car/1234").contentType(MediaType.APPLICATION_JSON)) //simular o get
                .andDo(print())
                .andExpect(jsonPath("$.maker", is("fiat")))
                .andExpect(jsonPath("$.model", is("punto")));

        verify(carService, times(1)).getCarDetails(Mockito.anyLong());
    }

}