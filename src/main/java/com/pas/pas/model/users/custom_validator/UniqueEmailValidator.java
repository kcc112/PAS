package com.pas.pas.model.users.custom_validator;

import com.pas.pas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.selectUserByEmail(s).isEmpty();
    }

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
    }
}
