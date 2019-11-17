package com.pas.pas.controller;

import com.pas.pas.model.users.User;
import com.pas.pas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("new")
    public String newForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return  "users/new";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("{id}")
    public String destroy(@PathVariable UUID id) {
        userService.destroyUser(id);
        return "redirect:/users";
    }
}
