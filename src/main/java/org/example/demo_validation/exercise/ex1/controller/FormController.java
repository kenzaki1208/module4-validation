package org.example.demo_validation.exercise.ex1.controller;

import jakarta.validation.Valid;
import org.example.demo_validation.exercise.ex1.service.IUserService;
import org.example.demo_validation.exercise.ex1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise/ex1/form")
public class FormController {
    @Autowired
    private IUserService userService;

    @GetMapping("")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "exercise/ex1/index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "exercise/ex1/index";
        }
        userService.save(user);
        model.addAttribute("user", user);
        return "exercise/ex1/result";
    }
}
