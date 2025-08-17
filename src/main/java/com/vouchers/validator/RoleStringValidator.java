package com.vouchers.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleStringValidator implements ConstraintValidator<com.vouchers.annotation.Role, String> {
    private static final Set<String> VALID_ROLES = Set.of("ADMIN", "MANAGER", "COLLABORATOR");

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        if (role == null) {
            return true;
        }
        return VALID_ROLES.contains(role.toUpperCase());
    }
}
