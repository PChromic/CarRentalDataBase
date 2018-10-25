package com.pchromic.Mappers;

import com.pchromic.Entity.Car;
import com.pchromic.TO.CarTO;

import java.util.List;

public interface CarMapper {

    CarTO toCarTO (Car car);

    Car toEntity (CarTO carTO);

    List<CarTO> map2TOs(List<Car> carEntities);

    List<Car> map2Entities(List<CarTO> carTOs);
}