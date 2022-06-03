package com.rest_project.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumTypeValidator implements ConstraintValidator<EnumType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.equals(Type.CRYPTO.toString()) || value.equals(Type.FIAT.toString());
    }
}
