package com.pas.pas.model.users;

import java.util.UUID;

public class Admin extends User {
    public Admin(String name, String surname, String type, UUID id) {
        super(name, surname, type, id);
    }
    public Admin() {}
}
