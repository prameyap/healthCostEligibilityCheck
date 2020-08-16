package com.nhs.healthcosteligibility.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegularAmountValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegularAmountConstraint {

    String message() default "Amount is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
