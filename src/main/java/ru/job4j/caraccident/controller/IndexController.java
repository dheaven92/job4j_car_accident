package ru.job4j.caraccident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.caraccident.service.AccidentService;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}