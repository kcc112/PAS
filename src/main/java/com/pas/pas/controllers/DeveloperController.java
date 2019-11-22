package com.pas.pas.controllers;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.service.interfaces.IDeveloperService;
import com.pas.pas.service.interfaces.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/developers")
@Controller
public class DeveloperController {

    private final IDeveloperService developerService;
    private final ITechnologyService technologyService;

    @Autowired
    public DeveloperController(IDeveloperService developerService, ITechnologyService technologyService) {
        this.developerService = developerService;
        this.technologyService = technologyService;
    }

    @GetMapping
    public String index(Model model) {
        List<Developer> developers = developerService.getAllDevelopers();
        model.addAttribute("developers", developers);
        return "developers/index";
    }

    @GetMapping("new")
    public String newForm(Model model) {
        Developer developer = new Developer();
        Technology technology = new Technology();
        List<Technology> technologies = technologyService.getAllTechnologies();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        return  "developers/new";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("developer") Developer developer,
                          @ModelAttribute("technology") Technology technology) {
        developerService.addDeveloper(developer, technology);
        return "redirect:/developers";
    }

    @PostMapping("{id}/delete")
    public String destroy(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
        return "redirect:/developers";
    }

    @PostMapping("{id}")
    public String update(@PathVariable UUID id,
                         @ModelAttribute("developer") Developer developer,
                         @ModelAttribute("technology") Technology technology) {
        developer.setId(id);
        developer.setDeveloperTechnology(technology);
        developerService.updateDeveloper(developer);
        return "redirect:/developers";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        Optional<Developer> developer = developerService.selectDeveloperById(id);
        if (developer.isPresent()) {
            List<Technology> technologies;
            if ( developer.get().getClass().equals(Backend.class)) {
                technologies = technologyService.getAllTechnologiesBackEnd();
            } else {
                technologies = technologyService.getAllTechnologiesFrontEnd();
            }
            model.addAttribute("technologies", technologies);
            model.addAttribute("developer", developer.get());
            model.addAttribute("technology", developer.get().getDeveloperTechnology());
            return  "developers/edit";
        } else {
            return  "redirect:/developers";
        }
    }
}
