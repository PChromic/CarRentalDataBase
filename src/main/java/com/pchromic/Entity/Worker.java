package com.pchromic.Entity;

import com.pchromic.Enum.WorkerPosition;
import com.pchromic.Validator.ValidateBirthDate;
import com.pchromic.Validator.ValidateFirstName;
import com.pchromic.Validator.ValidateLastName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name="worker_id")
    private long id;

    @ValidateFirstName
    @Column (name = "first_name", nullable = false)
    private String firstName;

    @ValidateLastName
    @Column (name = "last_name", nullable = false)
    private String lastName;

    @ValidateBirthDate
    private LocalDate birthDate;

    @NotNull
    @Enumerated (EnumType.STRING)
    private WorkerPosition workerPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }


    @ManyToMany ( mappedBy = "workers")
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
        car.getCarAdministrators().add(this);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
        car.getCarAdministrators().remove(this);
    }

    public Worker() {
    }


    public Worker(String firstName, String lastName, LocalDate birthDate, WorkerPosition workerPosition, List<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.workerPosition = workerPosition;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public WorkerPosition getWorkerPosition() {
        return workerPosition;
    }

    public void setWorkerPosition(WorkerPosition workerPosition) {
        this.workerPosition = workerPosition;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> carAdministrators) {
        this.cars = carAdministrators;
    }
}