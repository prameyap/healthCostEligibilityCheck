package com.nhs.healthcosteligibility.model;

import com.nhs.healthcosteligibility.validation.ValidRegularAmountConstraint;

@ValidRegularAmountConstraint
public class RegularAmount {

    //@NotNull(message = "Please select frequency")
    private Frequency frequency;
    //@Pattern(regexp = "^[0-9]*\\.?[0-9]+$", message = "Please enter valid amount")
    private String amount;

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
