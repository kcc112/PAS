package com.pas.pas.repository.interfaces;

import com.pas.pas.model.events.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventRepository {
    void addEvent(UUID id, Event event);
    void destroyEvent(UUID id);
    Optional<Event> selectEventById(UUID id);
    List<Event> getAllEvents();

    default void addEvent(Event event) {
        UUID id = UUID.randomUUID();
        addEvent(id, event);
    }
}
