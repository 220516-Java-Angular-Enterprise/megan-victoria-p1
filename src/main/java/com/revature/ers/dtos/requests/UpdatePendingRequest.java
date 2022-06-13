package com.revature.ers.dtos.requests;

import java.math.BigDecimal;

public class UpdatePendingRequest {
    private String id;
    private BigDecimal amount;
    private String description;

    public UpdatePendingRequest(){}


    public UpdatePendingRequest(String id, BigDecimal amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UpdatePendingRequest{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
