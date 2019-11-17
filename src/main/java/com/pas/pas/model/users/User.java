package com.pas.pas.model.users;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String type;
    private UUID id;

    User(String name, String surname, String type, UUID id) {
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.id = id;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
