package com.pas.pas.controllers;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.DeveloperType;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.service.interfaces.IDeveloperService;
import com.pas.pas.service.interfaces.ITechnologyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("page", "/developers/index");
        return "application/index";
    }

    @GetMapping("new/{type}")
    public String newForm(@PathVariable String type, Model model) {
        Developer developer;
        Technology technology = new Technology();
        DeveloperType developerType = new DeveloperType(type);
        List<Technology> technologies;
        if (type.equals("front-end")) {
            developer = new FrontEnd();
            technologies = technologyService.getAllTechnologiesFrontEnd();
        } else if (type.equals("back-end")) {
            developer = new Backend();
            technologies = technologyService.getAllTechnologiesBackEnd();
        } else {
            model.addAttribute("page", "/developers/index");
            return "application/index";
        }
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new");
        return  "application/index";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("developer") Developer developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developerType") DeveloperType developerType,
                          @ModelAttribute("technology") Technology technology,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new");
            return "application/index";
        }
        developerService.addDeveloper(developer, technology);
        return "redirect:/developers";
    }

    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
        return "redirect:/developers";
    }

    @PostMapping("{id}")
    private String update(@PathVariable UUID id,
                         @Validated @ModelAttribute("developer") Developer developer,
                          BindingResult bindingResult,
                         @ModelAttribute("technology") Technology technology,
                          Model model) {
        if (bindingResult.hasErrors()) {
            developer.setDeveloperId(id);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "/developers/edit");
            return "application/index";
        }
        developer.setDeveloperId(id);
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
            model.addAttribute("page", "/developers/edit");
            return  "/application/index";
        } else {
            model.addAttribute("page", "/developers/index");
            return  "redirect:/application/index";
        }
    }
}
