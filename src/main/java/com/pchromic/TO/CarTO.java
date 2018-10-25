package com.pchromic.TO;

import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;

import java.util.List;

public class CarTO {

    private long id;
    private CarMark mark;
    private CarModelType modelType;
    private Integer productionYear;
    private String color;
    private Integer engineCapacity ;
    private Integer horsepower;
    private Integer distance;
    private List<WorkerTO> workers;

    public CarTO() {
    }

    public CarTO(long id, CarMark mark, CarModelType modelType, Integer productionYear, String color,
                 Integer engineCapacity, Integer horsepower, Integer distance, List<WorkerTO> workers) {
        this.id = id;
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


    public List<WorkerTO> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerTO> workers) {
        this.workers = workers;
    }


}
