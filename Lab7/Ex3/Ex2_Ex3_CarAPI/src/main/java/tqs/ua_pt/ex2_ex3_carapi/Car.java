package tqs.ua_pt.ex2_ex3_carapi;
import javax.persistence.*;
import java.util.Objects;


@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carID;

    private String maker;
    private String model;

    public Car() {}

    public Car(String maker, String model) {
        this.model = model;
        this.maker = maker;
    }

    public Long getCarID() {
        return carID;
    }

    public void setCarID(Long carID) {
        this.carID = carID;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carID.equals(car.carID) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, maker, model);
    }
}