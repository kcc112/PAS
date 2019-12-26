package com.pas.pas.model.users;

import java.util.UUID;

public class ResourceAdministrator extends User {
    public ResourceAdministrator(String userName, String userSurname, String userType, UUID userId, String password) {
        super(userName, userSurname, userType, userId, password);
    }
}
