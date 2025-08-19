package com.vouchers.validator;

import com.vouchers.model.enums.Role;
import com.vouchers.model.enums.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StatusValidator implements ConstraintValidator<com.vouchers.annotation.Status, Status> {
    private static final Set<Status> VALID_STATUS = Set.of(Status.AVAILABLE, Status.ALLOCATED, Status.REDEEMED, Status.EXPIRED, Status.CANCELLED);

    @Override
    public boolean isValid(Status status, ConstraintValidatorContext constraintValidatorContext) {
        if (status == null) {
            return true;
        }
        return VALID_STATUS.contains(status);
    }
}
