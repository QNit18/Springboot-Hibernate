package com.anony18.springboot.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// You must declare
@Constraint(validatedBy = CourseCodeConstrainValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
// This is anotation
public @interface CourseCode {

    // define default Course code
    public String value() default "LUV";

    // define default Error message
    public String message() default "must start with LUV";

    // denfine Default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
