package com.pchromic.Entity.EntityBuilder;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Customer;
import com.pchromic.Entity.Office;
import com.pchromic.Entity.Rental;

import java.time.LocalDate;
import java.util.Date;

public final class RentalBuilder {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
    private Customer customer;
    private Office startOffice;
    private Office endOffice;
    private Car car;

    private RentalBuilder() {
    }

    public static RentalBuilder rentalBuilder() {
        return new RentalBuilder();
    }

    public RentalBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public RentalBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public RentalBuilder withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public RentalBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public RentalBuilder withStartOffice(Office startOffice) {
        this.startOffice = startOffice;
        return this;
    }

    public RentalBuilder withEndOffice(Office endOffice) {
        this.endOffice = endOffice;
        return this;
    }

    public RentalBuilder withCar(Car car) {
        this.car = car;
        return this;
    }

    public Rental build() {
        Rental rental = new Rental();
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setPrice(price);
        rental.setCustomer(customer);
        rental.setStartOffice(startOffice);
        rental.setEndOffice(endOffice);
        rental.setCar(car);
        return rental;
    }
}
