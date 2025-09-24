package org.example.demo_validation.practice.p1.controller;

import org.example.demo_validation.practice.p1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/practice/p1/user")
public class UserController {
    @GetMapping("/form")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("practice/p1/index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/validate")
    public ModelAndView checkValidation(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("practice/p1/index");
        }
        return new ModelAndView("practice/p1/result");
    }
}
