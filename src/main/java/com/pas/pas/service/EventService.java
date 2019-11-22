package com.pas.pas.service;

import com.pas.pas.model.events.Event;
import com.pas.pas.repository.interfaces.IEventRepository;
import com.pas.pas.service.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;

    @Autowired
    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public void addEvent(Event event) {
        LocalDate date = LocalDate.now();
        event.setStartData(date);
        event.setOngoing(true);
        eventRepository.addEvent(event);
    }

    @Override
    public void destroyEvent(UUID id) {
        eventRepository.destroyEvent(id);
    }

    @Override
    public void finishEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent()) {
            event.get().setOngoing(false);
            event.get().setEndData(LocalDate.now());
        }
    }
}
