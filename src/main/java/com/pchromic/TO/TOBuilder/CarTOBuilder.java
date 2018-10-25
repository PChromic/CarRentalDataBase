package com.pchromic.TO.TOBuilder;

import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import com.pchromic.TO.CarTO;
import com.pchromic.TO.WorkerTO;

import java.util.List;

public final class CarTOBuilder {
    private long id;
    private CarMark mark;
    private CarModelType modelType;
    private Integer productionYear;
    private String color;
    private Integer engineCapacity ;
    private Integer horsepower;
    private Integer distance;
    private List<WorkerTO> workers;

    private CarTOBuilder() {
    }

    public static CarTOBuilder aCarTO() {
        return new CarTOBuilder();
    }

    public CarTOBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CarTOBuilder withMark(CarMark mark) {
        this.mark = mark;
        return this;
    }

    public CarTOBuilder withModelType(CarModelType modelType) {
        this.modelType = modelType;
        return this;
    }

    public CarTOBuilder withProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public CarTOBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CarTOBuilder withEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
        return this;
    }

    public CarTOBuilder withHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public CarTOBuilder withDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public CarTOBuilder withWorkers(List<WorkerTO> workers) {
        this.workers = workers;
        return this;
    }

    public CarTO build() {
        CarTO carTO = new CarTO();
        carTO.setId(id);
        carTO.setMark(mark);
        carTO.setModelType(modelType);
        carTO.setProductionYear(productionYear);
        carTO.setColor(color);
        carTO.setEngineCapacity(engineCapacity);
        carTO.setHorsepower(horsepower);
        carTO.setDistance(distance);
        carTO.setWorkers(workers);
        return carTO;
    }
}
