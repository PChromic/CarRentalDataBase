
package com.pchromic.Entity;

import com.pchromic.Validator.ValidateBirthDate;
import com.pchromic.Validator.ValidateFirstName;
import com.pchromic.Validator.ValidateLastName;
import com.pchromic.Validator.ValidatePhoneNumber;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique = true, name ="id")
    private long id;

    @ValidateFirstName
    private String firstName;

    @ValidateLastName
    private String lastName;

    @Email
    private String email;

    @ValidateBirthDate
    @Column (name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @ValidatePhoneNumber
    @Column (name = "phone_number", nullable = false)
    private String phoneNumber;

    @CreditCardNumber
    @Column (name = "credit_card_number", nullable = false)
    private String creditCardNumber;

    @Column (name = "address", nullable = false)
    private String address;

    public Customer() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

