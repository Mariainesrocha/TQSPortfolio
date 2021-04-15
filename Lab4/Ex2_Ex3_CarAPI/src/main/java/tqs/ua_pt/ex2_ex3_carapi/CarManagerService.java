package tqs.ua_pt.ex2_ex3_carapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository repo;

    public Optional<Car> getCarDetails(Long id) {
        Car c = repo.findByCarID(id);
        if (c != null)
            return Optional.of(c);
        return null;
    }

    public List<Car> getAllCars(){
        return repo.findAll();
    };

    public Car save(Car c){
        return repo.save(c);
    };
}
