package com.pchromic.Validator;


import com.pchromic.Repository.CarRepository;
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

@NotNull (message = "Birth date is mandatory")
@Past
@DateTimeFormat(pattern = "yyyy-mm-dd")
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ValidateBirthDate {

    String message() default "must be a valid birth date." +
            "Found: ${validatedValue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
