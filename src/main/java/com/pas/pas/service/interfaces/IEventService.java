package com.pas.pas.service.interfaces;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.events.Event;
import com.pas.pas.model.technologies.Technology;

import java.util.List;
import java.util.UUID;

public interface IEventService {
    List<Event> getAllEvents();
    void addEvent(Event event);
    void destroyEvent(UUID id);
}
