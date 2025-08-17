package com.vouchers.annotation;

import com.vouchers.validator.RoleValidator;
import com.vouchers.validator.RoleStringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {RoleValidator.class, RoleStringValidator.class})
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    String message() default "Role must be ADMIN, MANAGER or COLLABORATOR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
