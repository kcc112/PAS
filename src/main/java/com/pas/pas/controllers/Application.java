package com.pas.pas.controllers;

import com.pas.pas.model.users.User;
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
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "application/login.html";
    }
}
