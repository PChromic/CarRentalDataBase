package com.pchromic.Service;


import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import com.pchromic.TO.CarTO;

import java.util.List;


public interface CarService {

     void addCar(CarTO car);

     void removeCar (Long carId);

     void updateCarDetails (CarTO car);

     CarTO getCarById (Long carId);

     List<CarTO> getCarByMark(CarMark mark);

     List<CarTO> getCarByType(CarModelType modelType);

     List<CarTO> getCarsByCarAdministrator(Long administratorId);

     void addAdministratorToCar(Long carId, Long administratorId);

}
