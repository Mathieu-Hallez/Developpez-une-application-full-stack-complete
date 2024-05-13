package com.orion.mdd.security;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = UpdatePasswordConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPasswordUpdate {
    String message() default "Invalid Password To Update";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
