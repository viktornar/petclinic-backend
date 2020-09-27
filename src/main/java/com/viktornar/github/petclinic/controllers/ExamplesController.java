package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.models.Owner;
import com.viktornar.github.petclinic.repositories.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/examples")
@RequiredArgsConstructor
public class ExamplesController {
    private final OwnersRepository ownersRepository;

    @GetMapping("/table")
    String getTable(Model model) {
        List<Owner> owners = ownersRepository.findAll();
        model.addAttribute("owners", owners);
        return "/examples/table";
    }

    @GetMapping("/tableWithLayout")
    String getTableWithLayout(Model model) {
        List<Owner> owners = ownersRepository.findAll();
        model.addAttribute("owners", owners);
        return "/examples/tableWithLayout";
    }
    
}