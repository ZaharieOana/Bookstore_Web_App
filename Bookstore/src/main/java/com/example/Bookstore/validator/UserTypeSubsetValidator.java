package com.example.Bookstore.validator;

import com.example.Bookstore.constants.UserType;
import com.example.Bookstore.model.BookType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class UserTypeSubsetValidator implements ConstraintValidator<UserTypeSubset, UserType> {
    private UserType[] subset;

    @Override
    public void initialize(UserTypeSubset constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(UserType userType, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.asList(subset).contains(userType);
    }
}
