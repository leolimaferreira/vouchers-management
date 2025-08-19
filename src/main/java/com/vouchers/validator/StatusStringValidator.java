package com.vouchers.validator;

import com.vouchers.annotation.Role;
import com.vouchers.annotation.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StatusStringValidator implements ConstraintValidator<Status, String> {
    private static final Set<String> VALID_STATUS = Set.of("AVAILABLE", "ALLOCATED", "REDEEMED", "EXPIRED", "CANCELLED");

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        if (status == null) {
            return true;
        }
        return VALID_STATUS.contains(status.toUpperCase());
    }
}