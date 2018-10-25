package com.pchromic.Repository.Customized;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Rental;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;

import java.util.List;

public interface CustomizedCarRepository {

    List<Car> getCarByMark(CarMark mark);

    List<Car> getCarByType(CarModelType type);

    List<Car> getCarByTypeAndMark(CarModelType modelType, CarMark carMark);

    List<Car> getCarByCarAdministrator(Long administratorId);

    void addAdministratorToCar (Long carId, Long administratorId);

    void addRentalToCar (Long carId, Rental rental);

    Car removeRentalFromCar (Long carId, Rental rental);

}
