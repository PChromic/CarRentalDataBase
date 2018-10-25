package com.pchromic.Validator.Impl;

import com.pchromic.Validator.ValidatePhoneNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidatePhoneNumber,String> {

    @Override
    public void initialize(ValidatePhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^[0-9]+$");
    }
}
