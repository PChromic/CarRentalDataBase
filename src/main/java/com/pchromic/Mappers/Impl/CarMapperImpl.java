package com.pchromic.Mappers.Impl;

import com.pchromic.Entity.Car;
import com.pchromic.Mappers.CarMapper;
import com.pchromic.TO.CarTO;
import com.pchromic.TO.TOBuilder.CarTOBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toEntity(CarTO carTO) {
        if (carTO == null)
            return null;
        Car car = new Car();
        car.setId(carTO.getId());
        car.setDistance(carTO.getDistance());
        car.setColor(carTO.getColor());
        car.setEngineCapacity(carTO.getEngineCapacity());
        car.setHorsepower(carTO.getHorsepower());
        car.setMark(carTO.getMark());
        car.setModelType(carTO.getModelType());
        car.setProductionYear(carTO.getProductionYear());

        return car;
    }
    @Override
    public CarTO toCarTO(Car car) {
        if (car == null)
            return null;
        return CarTOBuilder.aCarTO()
                .withColor(car.getColor())
                .withDistance(car.getDistance())
                .withEngineCapacity(car.getEngineCapacity())
                .withHorsepower(car.getHorsepower())
                .withId(car.getId())
                .withMark(car.getMark())
                .withModelType(car.getModelType())
                .withProductionYear(car.getProductionYear())
                .build();
    }

    @Override
    public List<CarTO> map2TOs(List<Car> carEntities) {
        return carEntities.stream().map(this::toCarTO).collect(Collectors.toList());
    }
    @Override
    public List<Car> map2Entities(List<CarTO> carTOs) {
        return carTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
