package com.pchromic.TO;


import java.util.List;

public class OfficeTO {


    private Long id;
    private String address;
    private String email;
    private String phoneNumber;
    private boolean deleted;
    private List<WorkerTO> workerTOList;

    public OfficeTO() {
    }


    public OfficeTO(Long id, String address, String email, String phoneNumber, boolean deleted, List<WorkerTO> workerTOList) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.deleted = deleted;
        this.workerTOList = workerTOList;
    }

    public List<WorkerTO> getWorkerTOList() {
        return workerTOList;
    }

    public void setWorkerTOList(List<WorkerTO> workerTOList) {
        this.workerTOList = workerTOList;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    }
