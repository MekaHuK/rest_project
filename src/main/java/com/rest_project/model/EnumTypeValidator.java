package com.rest_project.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTypeValidator implements ConstraintValidator<EnumType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        List<String> enumNames = Stream.of(Type.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        boolean isEnum = false;

        for(String type : enumNames){
            if(type.equals(value)){
                isEnum = true;
                break;
            }
        }

        return isEnum;
    }
}
