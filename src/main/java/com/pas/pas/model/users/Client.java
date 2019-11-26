package com.pas.pas.model.users;

import java.util.UUID;

public class Client extends User {
    public Client(String userName, String userSurname, String userType, UUID userId) {
        super(userName, userSurname, userType, userId);
    }
}
