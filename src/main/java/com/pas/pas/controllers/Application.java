package com.pas.pas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class Application {

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "application/login.html";
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("pageName", "home");
        return "application/home.html";
    }
}
