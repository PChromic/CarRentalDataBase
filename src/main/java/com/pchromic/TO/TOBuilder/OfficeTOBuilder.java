package com.pchromic.TO.TOBuilder;

import com.pchromic.TO.OfficeTO;
import com.pchromic.TO.WorkerTO;

import java.util.List;

public final class OfficeTOBuilder {
    private Long id;
    private String address;
    private String email;
    private String phoneNumber;
    private List<WorkerTO> workerTOList;

    private OfficeTOBuilder() {
    }

    public static OfficeTOBuilder anOfficeTO() {
        return new OfficeTOBuilder();
    }

    public OfficeTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OfficeTOBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public OfficeTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public OfficeTOBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OfficeTOBuilder withWorkerTOList(List<WorkerTO> workerTOList) {
        this.workerTOList = workerTOList;
        return this;
    }

    public OfficeTO build() {
        OfficeTO officeTO = new OfficeTO();
        officeTO.setId(id);
        officeTO.setAddress(address);
        officeTO.setEmail(email);
        officeTO.setPhoneNumber(phoneNumber);
        officeTO.setWorkerTOList(workerTOList);
        return officeTO;
    }
}
