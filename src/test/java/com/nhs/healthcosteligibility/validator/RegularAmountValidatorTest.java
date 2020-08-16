package com.nhs.healthcosteligibility.validator;

import com.nhs.healthcosteligibility.model.Frequency;
import com.nhs.healthcosteligibility.model.RegularAmount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegularAmountValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    public void given_valid_regularAmount_then_return_true() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setAmount("22");
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(0, violations.size());
    }

    @Test
    public void given_valid_regularAmount_then_return_false() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setAmount("22.33");
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(2, violations.size());
        assertEquals("Amount is invalid", violations.stream().findFirst().get().getMessage());
    }

    @Test
    public void given_amount_null_then_return_true() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(0, violations.size());
    }

    @Test
    public void given_frequency_null_then_return_true() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setAmount("22");
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(0, violations.size());
    }

    @Test
    public void given_frequency_monthly_then_return_true() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setAmount("22.11");
        regularAmount.setFrequency(Frequency.MONTH);
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(0, violations.size());
    }

    @Test
    public void given_frequency_weekly_then_return_true() {
        RegularAmount regularAmount = new RegularAmount();
        regularAmount.setAmount("22.11");
        regularAmount.setFrequency(Frequency.WEEK);
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(regularAmount);
        assertEquals(0, violations.size());
    }
}



