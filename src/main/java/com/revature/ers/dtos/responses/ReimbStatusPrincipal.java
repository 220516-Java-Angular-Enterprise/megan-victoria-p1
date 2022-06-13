package com.revature.ers.dtos.responses;

public class ReimbStatusPrincipal {
    private String id;
    private String author_id;
    private String status_id;
    private String description;

    public ReimbStatusPrincipal(){}

    public ReimbStatusPrincipal(String id, String author_id, String status_id, String description) {
        this.id = id;
        this.author_id = author_id;
        this.status_id = status_id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReimbStatusPrincipal{" +
                "id='" + id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
