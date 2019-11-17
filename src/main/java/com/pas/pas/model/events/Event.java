package com.pas.pas.model.events;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.users.User;

import javax.xml.crypto.Data;

public class Event {
    private User user;
    private Developer developer;
    private Data start;
    private Data end;

    public Event(User user, Developer developer, Data start) {
        this.user = user;
        this.developer = developer;
        this.start = start;
        this.end = null;
    }

    public User getUser() {
        return user;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public Data getStart() {
        return start;
    }

    public Data getEnd() {
        return end;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setStart(Data start) {
        this.start = start;
    }

    public void setEnd(Data end) {
        this.end = end;
    }
}
