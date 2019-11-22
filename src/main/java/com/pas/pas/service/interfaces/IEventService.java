package com.pas.pas.service.interfaces;

import com.pas.pas.model.events.Event;

import java.util.List;
import java.util.UUID;

public interface IEventService {
    List<Event> getAllEvents();
    void addEvent(Event event);
    void destroyEvent(UUID id);
}
