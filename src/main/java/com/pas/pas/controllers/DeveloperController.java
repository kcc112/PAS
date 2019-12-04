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
        model.addAttribute("page", "developers/index");
        model.addAttribute("pageName", "developers");
        return "application/index";
    }

    @GetMapping("new/{type}")
    public String newForm(@PathVariable String type, Model model) {
        List<Technology> technologies;
        DeveloperType developerType;
        Developer developer;
        model.addAttribute("pageName", "developers");
        if (type.equals("back-end")) {
            technologies = technologyService.getAllTechnologiesBackEnd();
            developer = new Backend();
            developerType = new DeveloperType("back-end");
        } else if (type.equals("front-end")) {
            technologies = technologyService.getAllTechnologiesFrontEnd();
            developer = new FrontEnd();
            developerType = new DeveloperType("front-end");
        } else {
            model.addAttribute("pageName", "developers");
            return "redirect: /developers";
        }
        Technology technology = new Technology();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new");
        return  "application/index";
    }

    @PostMapping("front-end")
    private String createFrontEnd(@Validated @ModelAttribute("developer") FrontEnd developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developer-type") DeveloperType developerType,
                          @ModelAttribute("technology") Technology technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new");
            return "application/index";
        }
        developerService.addDeveloper(developer, technology);
        return "redirect:/developers";
    }

    @PostMapping("back-end")
    private String createBackEnd(@Validated @ModelAttribute("developer") Backend developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developerType") DeveloperType developerType,
                          @ModelAttribute("technology") Technology technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new");
            return "application/index";
        }
        developerService.addDeveloper(developer, technology);
        return "redirect:/developers";
    }


    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        model.addAttribute("pageName", "developers");
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
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "/developers/edit");
            return "application/index";
        }
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
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "/developers/edit");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        developerService.updateDeveloper(developer);
        return "redirect:/developers";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        DeveloperType developerType;
        List<Technology> technologies;
        Optional<Developer> developer = developerService.selectDeveloperById(id);
        model.addAttribute("pageName", "developers");
        if (developer.isPresent()) {
            if (developer.get() instanceof FrontEnd) {
                technologies = technologyService.getAllTechnologiesFrontEnd();
                developerType = new DeveloperType("front-end");
            } else if (developer.get() instanceof Backend) {
                technologies = technologyService.getAllTechnologiesBackEnd();
                developerType = new DeveloperType("back-end");
            } else {
                return "redirect:/developers";
            }
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technologies);
            model.addAttribute("developer", developer.get());
            model.addAttribute("technology", developer.get().getDeveloperTechnology());
            model.addAttribute("page", "/developers/edit");
            return  "/application/index";
        } else {
            return "redirect:/developers";
        }
    }

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
        Optional<Developer> developer = developerService.selectDeveloperById(id);
        model.addAttribute("pageName", "developers");
        if (developer.isPresent()) {
            model.addAttribute("developer", developer.get());
            model.addAttribute("page", "developers/info");
            return "application/index";
        }
        return "redirect:/developers";
    }

    @GetMapping("search")
    public String search(@RequestParam(value="name", required=false) String name, Model model) {
        List<Developer> developers;
        model.addAttribute("pageName", "developers");
        if (name.isBlank()) {
            developers = developerService.getAllDevelopers();
        } else {
            developers = developerService.getDevelopersByName(name);
        }
        model.addAttribute("developers", developers);
        model.addAttribute("page", "developers/index");
        return "application/index";
    }
}
