package com.example.empleos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("message", "Bienvenido a EmpleosApp");
        model.addAttribute("date", new Date());
        return "home";
    }
}
