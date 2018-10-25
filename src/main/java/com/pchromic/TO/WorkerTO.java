package com.pchromic.TO;

import com.pchromic.Enum.WorkerPosition;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class WorkerTO {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private WorkerPosition workerPosition;
    private List<CarTO> cars;
    private OfficeTO office;

    public WorkerTO() {
    }

    public WorkerTO(long id, String firstName, String lastName,
                    LocalDate birthDate, WorkerPosition workerPosition, List<CarTO> cars, OfficeTO office) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.workerPosition = workerPosition;
        this.cars = cars;
        this.office = office;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public WorkerPosition getWorkerPosition() {
        return workerPosition;
    }

    public void setWorkerPosition(WorkerPosition workerPosition) {
        this.workerPosition = workerPosition;
    }

    public List<CarTO> getCars() {
        return cars;
    }

    public void setCars(List<CarTO> cars) {
        this.cars = cars;
    }

    public OfficeTO getOffice() {
        return office;
    }

    public void setOffice(OfficeTO office) {
        this.office = office;
    }
}
