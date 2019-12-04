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
import java.util.Optional;
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
        model.addAttribute("page", "events/index");
        model.addAttribute("pageName", "events");
        return "application/index";
    }

    @GetMapping("new")
    public String newForm(Model model) {
        Event event = new Event();
        User user = new User();
        Developer developer = new Developer();
        List<Developer> developers = developerService.getAllUnemployedDevelopers();
        List<User> users = userService.getAllActiveClients();
        model.addAttribute("pageName", "events");
        model.addAttribute("users", users);
        model.addAttribute("developers", developers);
        model.addAttribute("user", user);
        model.addAttribute("developer", developer);
        model.addAttribute("event", event);
        model.addAttribute("page", "events/new");
        return  "application/index";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("event") Event event,
                          @ModelAttribute("developer") Developer developer,
                          @ModelAttribute("user") User user,
                          Model model) {
        Optional<Developer> developerToSet = developerService.selectDeveloperById(developer.getDeveloperId());
        Optional<User> userToSet = userService.selectUserById(user.getUserId());
        model.addAttribute("pageName", "events");
        if (developerToSet.isPresent() && userToSet.isPresent()) {
            event.setUser(userToSet.get());
            event.setDeveloper(developerToSet.get());
            eventService.addEvent(event);
        }
        return "redirect:/events";
    }

    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        eventService.destroyEvent(id);
        model.addAttribute("pageName", "events");
        return "redirect:/events";
    }

    @PostMapping("{id}/end")
    public String endEvent(@PathVariable UUID id, Model model) {
        model.addAttribute("pageName", "events");
        eventService.finishEvent(id);
        return "redirect:/events";
    }
}
