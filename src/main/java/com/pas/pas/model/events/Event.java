package com.pas.pas.model.events;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.users.User;

import javax.xml.crypto.Data;
import java.util.UUID;

public class Event {
    private UUID id;
    private User user;
    private Developer developer;
    private Data startData;
    private Data endData;

    public Event(UUID id, User user, Developer developer, Data startData) {
        this.id = id;
        this.user = user;
        this.developer = developer;
        this.startData = startData;
        this.endData = null;
    }

    public Event() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public Data getStartData() {
        return startData;
    }

    public void setStartData(Data startData) {
        this.startData = startData;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Data getEndData() {
        return endData;
    }

    public void setEndData(Data endData) {
        this.endData = endData;
    }
}
