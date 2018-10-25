
package com.pchromic.Entity;

import com.pchromic.Validator.ValidateRentalDates;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table (name = "rental")
@ValidateRentalDates
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name="rental_id")
    private long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @Range (
            min = 100,
            max = 999999
    )
    @Column (name = "price", nullable = false)
    private Integer price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "start_office_id")
    private Office startOffice;
    public Office getStartOffice() {
        return startOffice;
    }

    public void setStartOffice(Office startOffice) {
        this.startOffice = startOffice;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "end_office_id")
    private Office endOffice;
    public Office getEndOffice() {
        return endOffice;
    }

    public void setEndOffice(Office endOffice) {
        this.endOffice = endOffice;
    }


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Rental() {
    }

    public Rental(LocalDate startDate, LocalDate endDate, Integer price, Customer customer, Office startOffice, Office endOffice, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.customer = customer;
        this.startOffice = startOffice;
        this.endOffice = endOffice;
        this.car = car;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

