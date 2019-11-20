package com.pas.pas.repository;

import com.pas.pas.model.events.Event;
import com.pas.pas.repository.interfaces.IEventRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EventRepository implements IEventRepository {

    private List<Event> events = new ArrayList<>();

    @Override
    public void addEvent(UUID id, Event event) {
        event.setId(id);
        events.add(event);
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<Event> eventToDelete = selectEventById(id);
        eventToDelete.ifPresent(event -> events.remove(event));
    }

    @Override
    public Optional<Event> selectEventById(UUID id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Event> getAllEvents() {
        return events;
    }
}
