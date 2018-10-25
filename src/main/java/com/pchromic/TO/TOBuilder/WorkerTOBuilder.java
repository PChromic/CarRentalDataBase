package com.pchromic.TO.TOBuilder;

import com.pchromic.Enum.WorkerPosition;
import com.pchromic.TO.CarTO;
import com.pchromic.TO.OfficeTO;
import com.pchromic.TO.WorkerTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public final class WorkerTOBuilder {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private WorkerPosition workerPosition;
    private List<CarTO> cars;
    private OfficeTO office;

    private WorkerTOBuilder() {
    }

    public static WorkerTOBuilder aWorkerTO() {
        return new WorkerTOBuilder();
    }

    public WorkerTOBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public WorkerTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public WorkerTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public WorkerTOBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public WorkerTOBuilder withWorkerPosition(WorkerPosition workerPosition) {
        this.workerPosition = workerPosition;
        return this;
    }

    public WorkerTOBuilder withCars(List<CarTO> cars) {
        this.cars = cars;
        return this;
    }
    public WorkerTOBuilder withOffice(OfficeTO office) {
        this.office = office;
        return this;
    }


    public WorkerTO build() {
        WorkerTO workerTO = new WorkerTO();
        workerTO.setId(id);
        workerTO.setFirstName(firstName);
        workerTO.setLastName(lastName);
        workerTO.setBirthDate(birthDate);
        workerTO.setWorkerPosition(workerPosition);
        workerTO.setCars(cars);
        workerTO.setOffice(office);
        return workerTO;
    }
}
