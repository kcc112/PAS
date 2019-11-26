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
        if (!event.getUser().getIsActive() || event.getDeveloper().isHired()) {
            return;
        }
        LocalDate date = LocalDate.now();
        event.setStartData(date);
        event.setOngoing(true);
        event.getDeveloper().setHired(true);
        eventRepository.addEvent(event);
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent() && event.get().getDeveloper() != null) {
            if (!event.get().getDeveloper().isHired()) {
                eventRepository.destroyEvent(id);
            }
        } else {
            if (event.isPresent()) {
                eventRepository.destroyEvent(id);
            }
        }
    }

    @Override
    public void finishEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent()) {
            event.get().setOngoing(false);
            if (event.get().getDeveloper() != null) {
                event.get().getDeveloper().setHired(false);
            }
            event.get().setEndData(LocalDate.now());
        }
    }
}
