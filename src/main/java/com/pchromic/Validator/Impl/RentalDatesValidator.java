package com.pchromic.Validator.Impl;


import com.pchromic.Entity.Rental;
import com.pchromic.Validator.ValidateRentalDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.sql.Date;
import java.time.LocalDate;


public class RentalDatesValidator implements ConstraintValidator<ValidateRentalDates,Rental> {

    @Override
    public void initialize(ValidateRentalDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(Rental rental, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = rental.getStartDate();
        LocalDate endDate = rental.getEndDate();

        return startDate.isBefore(endDate) || startDate.isEqual(endDate);
    }

}
