package com.example.myhouse24admin.validators.fileValidator.aboutPage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
@Constraint(validatedBy = ImagesExtensionValidator.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImagesExtension {
    String message() default "{validation-file-extension-not-valid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
