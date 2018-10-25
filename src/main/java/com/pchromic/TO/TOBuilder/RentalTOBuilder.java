package com.pchromic.TO.TOBuilder;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Customer;
import com.pchromic.Entity.Office;
import com.pchromic.TO.RentalTO;

import java.time.LocalDate;
import java.util.Date;

public final class RentalTOBuilder {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
    private Customer customer;
    private Office startOffice;
    private Office endOffice;
    private Car car;

    private RentalTOBuilder() {
    }

    public static RentalTOBuilder aRentalTO() {
        return new RentalTOBuilder();
    }

    public RentalTOBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RentalTOBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public RentalTOBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public RentalTOBuilder withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public RentalTOBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public RentalTOBuilder withStartOffice(Office startOffice) {
        this.startOffice = startOffice;
        return this;
    }

    public RentalTOBuilder withEndOffice(Office endOffice) {
        this.endOffice = endOffice;
        return this;
    }

    public RentalTOBuilder withCar(Car car) {
        this.car = car;
        return this;
    }

    public RentalTO build() {
        return new RentalTO(id, startDate, endDate, price, customer, startOffice, endOffice, car);
    }
}
