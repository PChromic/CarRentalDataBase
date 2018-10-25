package com.pchromic.Entity;


import com.pchromic.Validator.ValidatePhoneNumber;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "office")
@SQLDelete(sql =
        "UPDATE office " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique = true, name = "id")
    private Long id;

    @Column (name = "address", nullable = false)
    private String address;

    @NotNull
    @Email
    @Column (name = "email", nullable = false)
    private String email;

    @ValidatePhoneNumber
    @Column (name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column (name = "deleted", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "office", orphanRemoval = true)
    private List<Worker> workers = new ArrayList<>();

    public void addWorker(Worker worker){
        workers.add(worker);
        worker.setOffice(this);
    }

    public void removeWorker (Worker worker){
        workers.remove(worker);
        worker.setOffice(null);
    }

    @PreRemove
    public void deleteOffice() {
        this.deleted = true;
    }


    public Office() {
    }

    public Office(String address, String email, String phoneNumber, boolean deleted, List<Worker> workers) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.deleted = deleted;
        this.workers = workers;
    }

    public Long getId() {
        return id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Worker> getOfficeWorkers(){
        return workers;
    }

    public void setOfficeWorkers(List<Worker> workers) {
        this.workers = workers;
    }


}