package com.pas.pas.controllers;

import com.pas.pas.model.technologies.Technology;
import com.pas.pas.service.interfaces.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/technologies")
@Controller
public class TechnologyController {

    private final ITechnologyService technologyService;

    @Autowired
    public TechnologyController(ITechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public String index(Model model) {
        List<Technology> technologies = technologyService.getAllTechnologies();
        model.addAttribute("technologies", technologies);
        model.addAttribute("page", "technologies/index");
        model.addAttribute("pageName", "technologies");
        return "application/index";
    }
}
