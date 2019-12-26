package com.pas.pas.model.users;

import com.pas.pas.model.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Client extends User {

    private List<Event> events = new ArrayList<>();

    public Client(String userName, String userSurname, String userType, UUID userId, String password) {
        super(userName, userSurname, userType, userId, password);
    }

    public Client() { }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        Optional<Event> eventToRemove =  events.stream()
                                .filter(var -> var.getId().equals(event.getId()))
                                .findFirst();
        eventToRemove.ifPresent(value -> events.remove(value));
    }
}
