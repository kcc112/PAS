package com.pas.pas.controllers;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.DeveloperType;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.service.interfaces.IDeveloperService;
import com.pas.pas.service.interfaces.ITechnologyService;
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

    @GetMapping("new/front-end")
    public String newFormFrontEnd(Model model) {
        FrontEnd developer = new FrontEnd();
        Technology technology = new Technology();
        DeveloperType developerType = new DeveloperType("front-end");
        List<Technology> technologies = technologyService.getAllTechnologiesFrontEnd();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new");
        return  "application/index";
    }

    @GetMapping("new/back-end")
    public String newFormBackEnd(Model model) {
        Backend developer = new Backend();
        Technology technology = new Technology();
        DeveloperType developerType = new DeveloperType("back-end");
        List<Technology> technologies = technologyService.getAllTechnologiesBackEnd();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new");
        return  "application/index";
    }

    @PostMapping("create/back-end")
    private String create(@Validated @ModelAttribute("developer") Backend developer,
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

    @PostMapping("create/front-end")
    private String create(@Validated @ModelAttribute("developer") FrontEnd developer,
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

    @PostMapping("{id}/back-end")
    private String updateBackEnd(@PathVariable UUID id,
                         @Validated @ModelAttribute("developer") Backend developer,
                          BindingResult bindingResult,
                         @ModelAttribute("technology") Technology technology,
                                 @ModelAttribute DeveloperType developerType,
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

    @PostMapping("{id}/front-end")
    private String updateFrontEnd(@PathVariable UUID id,
                          @Validated @ModelAttribute("developer") FrontEnd developer,
                          BindingResult bindingResult,
                          @ModelAttribute("technology") Technology technology,
                          @ModelAttribute("developerType") DeveloperType developerType,
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
                DeveloperType developerType = new DeveloperType("back-end");
                model.addAttribute("developerType", developerType);
                technologies = technologyService.getAllTechnologiesBackEnd();
            } else {
                DeveloperType developerType = new DeveloperType("front-end");
                model.addAttribute("developerType", developerType);
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

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
        Optional<Developer> developer = developerService.selectDeveloperById(id);
        if (developer.isPresent()) {
            DeveloperType developerType;
            if (developer.get().getDeveloperTechnology().getTechnologyName().equals("React")) {
                developerType = new DeveloperType("front-end");
            } else {
                developerType = new DeveloperType("back-end");
            }
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer.get());
            model.addAttribute("page", "developers/info");
            return "application/index";
        }
        return  "redirect:/application/index";
    }
}
