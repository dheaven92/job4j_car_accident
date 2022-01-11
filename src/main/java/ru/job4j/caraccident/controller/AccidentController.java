package ru.job4j.caraccident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.service.AccidentService;

@Controller
public class AccidentController {

    @Autowired
    private AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.findAllAccidentTypes());
        return "accident/create";
    }

    @GetMapping("/update")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findAccidentById(id));
        model.addAttribute("types", accidentService.findAllAccidentTypes());
        return "accident/update";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Accident accident) {
        accidentService.saveAccident(accident);
        return "redirect:/";
    }
}