
package com.pchromic.Entity;

import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name="car_id")
    private long id;

    @NotNull
    @Enumerated (EnumType.STRING)
    @Column (name = "mark", nullable = false)
    private CarMark mark;

    @NotNull
    @Column (name = "model_type", nullable = false)
    @Enumerated (EnumType.STRING)
    private CarModelType modelType;

    @NotNull
    @Range (min = 2000, max = 2018, message = "Production year must be between 2000 and 2018")
    @Column (name = "production_year", nullable = false)
    private Integer productionYear;

    @NotNull
    @Size (min = 3, max = 9)
    @Column (name = "color", nullable = false)
    private String color;

    @NotNull
    @Range(min = 700, max = 7000, message = "Engine capacity value must be between 700 and 7000")
    @Column (name = "engine_capacity", nullable = false)
    private Integer engineCapacity ;

    @NotNull
    @Range (min = 50, max = 650, message = "Horsepower value must be between 50 and 650")
    @Column (name = "horsepower", nullable = false)
    private Integer horsepower;

    @Min(1)
    @Column (name = "distance", nullable = false)
    private Integer distance;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "car", orphanRemoval = true)
    private List<Rental> rentals = new ArrayList<>();

    public void addRental (Rental rental){
        rentals.add(rental);
        rental.setCar(this);
    }

    public void removeRental (Rental rental){
        rentals.remove(rental);
        rental.setCar(null);
    }


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable (
            name = "Worker_x_car",
            joinColumns = { @JoinColumn(name = "fk_car") },
            inverseJoinColumns = {@JoinColumn(name = "fk_worker")}
    )
    private List<Worker> workers = new ArrayList<>();

    public void addWorker(Worker worker){
        workers.add(worker);
        worker.getCars().add(this);
    }

    public void removeWorker(Worker worker){
        workers.remove(worker);
        worker.getCars().remove(this);
    }


    public Car() {
    }

    public Car(CarMark mark, CarModelType modelType, Integer productionYear, String color,
               Integer engineCapacity, Integer horsepower, Integer distance, List<Worker> workers) {
        this.mark = mark;
        this.modelType = modelType;
        this.productionYear = productionYear;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.horsepower = horsepower;
        this.distance = distance;
        this.workers = workers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarMark getMark() {
        return mark;
    }

    public void setMark(CarMark mark) {
        this.mark = mark;
    }

    public CarModelType getModelType() {
        return modelType;
    }

    public void setModelType(CarModelType modelType) {
        this.modelType = modelType;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }


    public List<Worker> getCarAdministrators() {
        return new ArrayList<>(workers);
    }

    public void setCarAdministrators (List<Worker> administrators) {
        this.workers = administrators;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}

