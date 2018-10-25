package com.pchromic.Entity.EntityBuilder;

import com.pchromic.Entity.Customer;

import java.time.LocalDate;
import java.util.Date;

public final class CustomerBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private String creditCardNumber;
    private String address;

    private CustomerBuilder() {
    }

    public static CustomerBuilder customerBuilder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public CustomerBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder withCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public CustomerBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setBirthDate(birthDate);
        customer.setPhoneNumber(phoneNumber);
        customer.setCreditCardNumber(creditCardNumber);
        customer.setAddress(address);
        return customer;
    }
}
