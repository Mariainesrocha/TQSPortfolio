package tqs.ua_pt.ex2_ex3_carapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarRestController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path="/cars", produces="application/json")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Optional<Car> getCarDetails(@PathVariable(value = "id") Long id){ return carManagerService.getCarDetails(id); }
}