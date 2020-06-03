package com.example.empleos.controller;

import com.example.empleos.model.Category;
import com.example.empleos.service.CategoriesService;
import com.example.empleos.service.CategoriesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private CategoriesServiceInterface categoriesService;

    //@GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model){
        model.addAttribute("categories", categoriesService.findAll());
        return "categories/listCategories";
    }

    //@GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        return "categories/formCategory";
    }

    //@PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Category category, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                System.out.println("Error: "+error.getDefaultMessage());
            }
            return "categories/formCategory";
        }
        attributes.addFlashAttribute ("msg", "Registro Guardado");
        categoriesService.save(category);
        return "redirect:/categories/index";
    }
}
