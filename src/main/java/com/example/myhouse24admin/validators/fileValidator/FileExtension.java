package com.example.myhouse24admin.validators.fileValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileExtensionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExtension {

    String message() default "{validation-file-extension-not-valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
