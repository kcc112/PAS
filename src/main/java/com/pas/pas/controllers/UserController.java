package com.pas.pas.controllers;

import com.pas.pas.model.users.User;
import com.pas.pas.service.interfaces.IUserService;
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

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("new")
    public String _new(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return  "users/new";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("{id}/delete")
    public String destroy(@PathVariable UUID id) {
        userService.destroyUser(id);
        return "redirect:/users";
    }

    @PostMapping("{id}")
    public String update(@PathVariable UUID id, @ModelAttribute("user") User user) {
        user.setUserId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        User user = new User();
        user.setUserId(id);
        model.addAttribute("user", user);
        return  "users/edit";
    }

    //Custom controllers region
    @GetMapping("{id}/activate-or-deactivate")
    public String activateOrDeactivate(@PathVariable UUID id) {
        userService.activateOrDeactivateUser(id);
        return "redirect:/users";
    }
}
