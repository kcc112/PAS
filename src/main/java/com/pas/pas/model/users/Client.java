package com.pas.pas.model.users;

import java.util.UUID;

public class Client extends User {
    public Client(String name, String surname, String type, UUID id) {
        super(name, surname, type, id);
    }
    public Client() { }
}
