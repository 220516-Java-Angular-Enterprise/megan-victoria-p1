package com.revature.ers.dtos.requests;

import java.sql.Timestamp;

public class NewReimbursementRequest {
    private String author_id;
    private String status_id;
    private String type_id;
    private Timestamp submitted;
    private Number amount;
    private String description;

    public NewReimbursementRequest(){

    }

    public NewReimbursementRequest(String author_id, String status_id, String type_id, Timestamp submitted, Number amount, String description) {
        this.author_id = author_id;
        this.status_id = status_id;
        this.type_id = type_id;
        this.submitted = submitted;
        this.amount = amount;
        this.description = description;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
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
        return "NewReimbursementRequest{" +
                "author_id='" + author_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", submitted=" + submitted +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
