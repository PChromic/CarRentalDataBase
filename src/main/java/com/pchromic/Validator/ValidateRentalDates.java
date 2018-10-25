package com.pchromic.Validator;

import com.pchromic.Validator.Impl.RentalDatesValidator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@DateTimeFormat(pattern = "yyyy-mm-dd")
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = {RentalDatesValidator.class})
public @interface ValidateRentalDates {

    String message() default "End date [${validatedValue.getEndDate()}] " +
            "has to be after start date [${validatedValue.getStartDate()}] or even!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
