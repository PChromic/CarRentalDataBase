package com.pchromic.Repository.Customized;

import com.pchromic.Entity.Car;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface CustomizedRentalRepository {

    List<Car> getCarsRentedInGivenTimePeriod(LocalDate startDate, LocalDate endDate);
    List<Long> findCarsRentedByMoreThanGivenNumberOfCustomers(Long customersNumber);
}
