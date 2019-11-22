package com.pas.pas.model.events;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.users.User;

import java.time.LocalDate;
import java.util.UUID;

public class Event {
    private UUID id;
    private User user;
    private Developer developer;
    private LocalDate startData;
    private LocalDate endData;

    public Event(UUID id, User user, Developer developer, LocalDate startData) {
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

    public LocalDate getStartData() {
        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getEndData() {
        return endData;
    }

    public void setEndData(LocalDate endData) {
        this.endData = endData;
    }
}
