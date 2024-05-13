package com.orion.mdd.security;

import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class UpdatePasswordConstraintValidator implements ConstraintValidator<ValidPasswordUpdate, String> {
    @Override
    public boolean isValid(@Nullable String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null) {
            return true;
        }

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 255),
                new CharacterCharacteristicsRule(
                        5,
                        new CharacterRule(EnglishCharacterData.Alphabetical),
                        new CharacterRule(EnglishCharacterData.Digit),
                        new CharacterRule(EnglishCharacterData.Special),
                        new CharacterRule(EnglishCharacterData.UpperCase),
                        new CharacterRule(EnglishCharacterData.LowerCase)
                ),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        messages.remove(messages.size()-1);
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(messages.get(0))
                .addConstraintViolation();
        return false;
    }
}