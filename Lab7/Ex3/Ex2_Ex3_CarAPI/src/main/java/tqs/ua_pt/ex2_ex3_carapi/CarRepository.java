package tqs.ua_pt.ex2_ex3_carapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {
    public List<Car> findAll();
    public Car findByCarID(Long id);
}
