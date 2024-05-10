package com.example.myhouse24admin.validators.socialsValidation.viber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = ViberRegexValidValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViberRegexValid {
    String message() default "{validation-viber-regex}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
