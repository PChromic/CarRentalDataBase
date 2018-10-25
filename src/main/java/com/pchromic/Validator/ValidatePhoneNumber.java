package com.pchromic.Validator;


import com.pchromic.Validator.Impl.PhoneNumberValidator;
import com.pchromic.Validator.Impl.RentalDatesValidator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Size(
        min = 9,
        max = 12,
        message = "Please write number in format like: 123 456 789"
)
@NotNull (message = "Phone number is mandatory")
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {PhoneNumberValidator.class})
@Documented
public @interface ValidatePhoneNumber {

    String message() default "must be a valid phone number." +
            "Found: ${validatedValue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
