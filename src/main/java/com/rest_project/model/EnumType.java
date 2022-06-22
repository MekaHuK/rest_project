package com.rest_project.model;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EnumTypeValidator.class)
@Documented
public @interface EnumType {
    String message() default "field Type is incorrect: use valid values";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
