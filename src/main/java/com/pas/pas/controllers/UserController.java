package com.pas.pas.controllers;

import com.pas.pas.model.users.User;
import com.pas.pas.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        model.addAttribute("page", "users/index");
        return "application/index";
    }

    @GetMapping("new")
    public String _new(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("page", "users/new");
        return "application/index";
    }

    @PostMapping
    private String create(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", "users/new");
            return "application/index";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id) {
        userService.destroyUser(id);
        return "redirect:/users";
    }

    @PostMapping("{id}")
    private String update(@PathVariable UUID id,
                          @Validated @ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          Model model) {
        user.setUserId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("page", "users/edit");
            return "application/index";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        User user = new User();
        user.setUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("page", "users/edit");
        return "application/index";
    }

    //Custom controllers region
    @GetMapping("{id}/activate-or-deactivate")
    public String activateOrDeactivate(@PathVariable UUID id) {
        userService.activateOrDeactivateUser(id);
        return "redirect:/users";
    }

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
        Optional<User> user =  userService.selectUserById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        model.addAttribute("page", "users/info");
        return "application/index";
    }

    @GetMapping("search")
    public String search(@RequestParam(value="name", required=false) String name, Model model) {
        List<User> users;
        if (name.isBlank()) {
            users = userService.getAllUsers();
        } else {
            users = userService.getUsersByName(name);
        }
        model.addAttribute("users", users);
        model.addAttribute("page", "users/index");
        return "application/index";
    }
}
