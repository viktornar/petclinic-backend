package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.models.User;
import com.viktornar.github.petclinic.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register") 
    public String postRegister(
        @ModelAttribute("user") @Valid User user, 
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            return "register";
        }


        userService.save(user);
        return "redirect:login";
    }

}