package com.vouchers.validator;

import com.vouchers.model.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleValidator implements ConstraintValidator<com.vouchers.annotation.Role, Role> {
    private static final Set<Role> VALID_ROLES = Set.of(Role.ADMIN, Role.MANAGER, Role.COLLABORATOR);

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        if (role == null) {
            return true;
        }
        return VALID_ROLES.contains(role);
    }
}
