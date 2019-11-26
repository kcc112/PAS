package com.pas.pas.model.users;

import java.util.UUID;

public class Admin extends User {
    public Admin(String userName, String userSurname, String userType, UUID userId) {
        super(userName, userSurname, userType, userId);
    }
}
