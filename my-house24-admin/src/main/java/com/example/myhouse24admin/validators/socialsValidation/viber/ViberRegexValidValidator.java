package com.example.myhouse24admin.validators.socialsValidation.viber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViberRegexValidValidator implements ConstraintValidator<ViberRegexValid, String> {
    @Override
    public boolean isValid(String viber, ConstraintValidatorContext constraintValidatorContext) {
        if(viber.isEmpty()){
            return true;
        }
        Pattern numberPattern = Pattern.compile("\\+?380(50)?(66)?(95)?(99)?(67)?(68)?(96)?(97)?(98)?(63)?(93)?(73)?[0-9]{7}");
        Matcher numberMatcher = numberPattern.matcher(viber);
        return numberMatcher.matches();
    }
}
