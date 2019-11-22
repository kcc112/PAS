package com.pas.pas.model.users;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

    @NotBlank
    private String userName;
    @NotBlank
    private String userSurname;
    private String userType;
    private UUID id;

    User(String userName, String userSurname, String userType, UUID id) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userType = userType;
        this.id = id;
    }

    public User() {}

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return userName + " " + userSurname + " " + userType;
    }
}
