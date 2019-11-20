package com.pas.pas.controllers;

import com.pas.pas.model.events.Event;
import com.pas.pas.service.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/events")
@Controller
public class EventController {

    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String index(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("new")
    public String newForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return  "events/new";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("event") Event event) {
        eventService.addEvent(event);
        return "redirect:/events";
    }

    @PostMapping("{id}")
    public String destroy(@PathVariable UUID id) {
        eventService.destroyEvent(id);
        return "redirect:/events";
    }
}
