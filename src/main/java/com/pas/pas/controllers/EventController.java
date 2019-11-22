package com.pas.pas.controllers;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.events.Event;
import com.pas.pas.model.users.User;
import com.pas.pas.service.interfaces.IDeveloperService;
import com.pas.pas.service.interfaces.IEventService;
import com.pas.pas.service.interfaces.IUserService;
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
    private final IUserService userService;
    private final IDeveloperService developerService;

    @Autowired
    public EventController(IEventService eventService, IUserService userService, IDeveloperService developerService) {
        this.userService = userService;
        this.developerService = developerService;
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
        List<Developer> developers = developerService.getAllDevelopers();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("developers", developers);
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
