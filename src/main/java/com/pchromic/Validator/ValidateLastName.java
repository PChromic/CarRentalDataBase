package com.pchromic.Validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@NotNull
@Length(
        min = 2,
        max = 20,
        message = "Last name should be between 2 and 20 characters"
)
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ValidateLastName {

    String message() default "must be a valid language." +
            "Found: ${validatedValue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
