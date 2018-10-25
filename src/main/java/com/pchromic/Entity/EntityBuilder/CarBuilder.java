package com.pchromic.Entity.EntityBuilder;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;

import java.util.ArrayList;
import java.util.List;

public final class CarBuilder {
    private CarMark mark;
    private CarModelType modelType;
    private Integer productionYear;
    private String color;
    private Integer engineCapacity;
    private Integer horsepower;
    private Integer distance;
    private List<Worker> workers = new ArrayList<>();

    private CarBuilder() {
    }

    public static CarBuilder carBuilder() {
        return new CarBuilder();
    }

    public CarBuilder withMark(CarMark mark) {
        this.mark = mark;
        return this;
    }

    public CarBuilder withModelType(CarModelType modelType) {
        this.modelType = modelType;
        return this;
    }

    public CarBuilder withProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public CarBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder withEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
        return this;
    }

    public CarBuilder withHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public CarBuilder withDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public CarBuilder withWorkers(List<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public Car build() {
        return new Car(mark, modelType, productionYear, color, engineCapacity, horsepower, distance, workers);
    }
}
