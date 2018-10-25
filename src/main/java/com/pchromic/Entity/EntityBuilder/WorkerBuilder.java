package com.pchromic.Entity.EntityBuilder;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Office;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.WorkerPosition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class WorkerBuilder {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private WorkerPosition workerPosition;
    private Office office;
    private List<Car> cars = new ArrayList<>();

    private WorkerBuilder() {
    }

    public static WorkerBuilder workerBuilder() {
        return new WorkerBuilder();
    }

    public WorkerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public WorkerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public WorkerBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public WorkerBuilder withWorkerPosition(WorkerPosition workerPosition) {
        this.workerPosition = workerPosition;
        return this;
    }

    public WorkerBuilder withOffice(Office office) {
        this.office = office;
        return this;
    }

    public WorkerBuilder withCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    public Worker build() {
        Worker worker = new Worker();
        worker.setFirstName(firstName);
        worker.setLastName(lastName);
        worker.setBirthDate(birthDate);
        worker.setWorkerPosition(workerPosition);
        worker.setOffice(office);
        worker.setCars(cars);
        return worker;
    }
}
