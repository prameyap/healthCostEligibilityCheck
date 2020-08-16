package com.nhs.healthcosteligibility.validation;

import com.nhs.healthcosteligibility.model.RegularAmount;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class RegularAmountValidator implements ConstraintValidator<ValidRegularAmountConstraint, RegularAmount> {

    @Override
    public void initialize(ValidRegularAmountConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegularAmount form, ConstraintValidatorContext ctx) {
        if (!StringUtils.isEmpty(form.getAmount()) && form.getFrequency() != null) {
            BigDecimal frequency = BigDecimal.valueOf(form.getFrequency().getValue());
            BigDecimal amountInPence = new BigDecimal(form.getAmount()).multiply(BigDecimal.valueOf(100));
            if (amountInPence.remainder(frequency).compareTo(BigDecimal.ZERO) != 0) {
                ctx.buildConstraintViolationWithTemplate("Amount is invalid").addPropertyNode("amount")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
