package com.pchromic.Entity.EntityBuilder;

import com.pchromic.Entity.Office;
import com.pchromic.Entity.Worker;

import java.util.ArrayList;
import java.util.List;

public final class OfficeBuilder {
    private String address;
    private String email;
    private String phoneNumber;
    private List<Worker> workers = new ArrayList<>();

    private OfficeBuilder() {
    }

    public static OfficeBuilder officeBuilder() {
        return new OfficeBuilder();
    }

    public OfficeBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public OfficeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public OfficeBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OfficeBuilder withWorkers(List<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public Office build() {
        return new Office(address, email, phoneNumber,false, workers);
    }
}
