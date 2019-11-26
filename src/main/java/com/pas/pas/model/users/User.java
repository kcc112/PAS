package com.pas.pas.model.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class User {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 12)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid name")
    private String userName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 12)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid surname")
    private String userSurname;

    @NotBlank
    private String userType;

    private UUID userId;
    private boolean isActive;

    User(String userName, String userSurname, String userType, UUID userId, boolean isActive) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userType = userType;
        this.userId = userId;
        this.isActive = isActive;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return userName + " " + userSurname + " " + userType;
    }
}
