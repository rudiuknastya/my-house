package com.example.myhouse24admin.validators.fileValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = BlockImageExtensionValidator.class)
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BlockImageExtension {
    String message() default "{validation-file-extension-not-valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
