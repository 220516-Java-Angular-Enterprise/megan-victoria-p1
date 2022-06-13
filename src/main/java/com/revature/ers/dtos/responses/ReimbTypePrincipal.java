package com.revature.ers.dtos.responses;

public class ReimbTypePrincipal {
    private String id;
    private String author_id;
    private String type_id;
    private String description;

    public ReimbTypePrincipal(){}


    public ReimbTypePrincipal(String id, String author_id, String type_id, String description) {
        this.id = id;
        this.author_id = author_id;
        this.type_id = type_id;
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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReimbTypePrincipal{" +
                "id='" + id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
