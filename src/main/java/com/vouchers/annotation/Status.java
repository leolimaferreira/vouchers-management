package com.vouchers.annotation;

import com.vouchers.validator.StatusStringValidator;
import com.vouchers.validator.StatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StatusValidator.class, StatusStringValidator.class})
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {
    String message() default "Status must AVAILABLE, ALLOCATED, REDEEMED, EXPIRED or CANCELLED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
