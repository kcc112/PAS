package com.pas.pas.service;

import com.pas.pas.model.events.Event;
import com.pas.pas.model.users.Client;
import com.pas.pas.repository.interfaces.IEventRepository;
import com.pas.pas.service.interfaces.IEventService;
import com.pas.pas.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final IUserService userService;

    @Autowired
    public EventService(IEventRepository eventRepository, IUserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
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
        event.setStartDate(date);
        if (event.getUser() instanceof Client) {
            ((Client) event.getUser()).addEvent(event);
        }
        event.getDeveloper().setHired(true);
        eventRepository.addEvent(event);
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent() && event.get().getEndDate() == null) {
            event.get().getDeveloper().setHired(false);
            if (event.get().getUser() instanceof Client) {
                ((Client) event.get().getUser()).removeEvent(event.get());
            }
            eventRepository.destroyEvent(id);
        }
    }

    @Override
    public void finishEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent()) {
            if (event.get().getDeveloper() != null) {
                event.get().getDeveloper().setHired(false);
            }
            event.get().setEndDate(LocalDate.now());
        }
    }
}
