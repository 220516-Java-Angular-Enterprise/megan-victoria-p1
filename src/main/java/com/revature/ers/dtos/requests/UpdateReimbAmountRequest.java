package com.revature.ers.dtos.requests;

import java.math.BigDecimal;

public class UpdateReimbAmountRequest {
    private BigDecimal amount;
    private String id;

    public UpdateReimbAmountRequest(){}


    public UpdateReimbAmountRequest(BigDecimal amount, String id) {
        this.amount = amount;
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateReimbAmountRequest{" +
                "amount=" + amount +
                ", id='" + id + '\'' +
                '}';
    }
}
