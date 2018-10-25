package com.pchromic.TO;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Customer;
import com.pchromic.Entity.Office;

import java.time.LocalDate;
import java.util.Date;

public class RentalTO {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
    private Customer customer;
    private Office startOffice;
    private Office endOffice;
    private Car car;

    public RentalTO() {
    }

    public RentalTO(long id, LocalDate startDate, LocalDate endDate, Integer price, Customer customer, Office startOffice, Office endOffice, Car car) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.customer = customer;
        this.startOffice = startOffice;
        this.endOffice = endOffice;
        this.car = car;
    }
}
