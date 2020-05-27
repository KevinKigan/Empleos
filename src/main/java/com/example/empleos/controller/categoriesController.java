package com.example.empleos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/categories")
public class categoriesController {
    //@GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model){
        return "categories/listCategories";
    }

    //@GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        return "categories/formCategory";
    }

    //@PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model){
        return "categories/listCategories";
    }
}
