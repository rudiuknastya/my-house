package com.example.myhouse24user.validators.fileValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = ImageExtensionValidValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageExtensionValid {
    String message() default "{validation-file-extension-not-valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
