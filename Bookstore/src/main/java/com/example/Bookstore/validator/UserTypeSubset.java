package com.example.Bookstore.validator;

import com.example.Bookstore.constants.UserType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserTypeSubsetValidator.class)
public @interface UserTypeSubset {
    UserType[] anyOf();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
