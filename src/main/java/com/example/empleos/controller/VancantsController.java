package com.example.empleos.controller;

import com.example.empleos.model.Vacant;
import com.example.empleos.service.CategoriesServiceInterface;
import com.example.empleos.service.VacantsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/vacants")
public class VancantsController {

    @Autowired
    private VacantsServiceInterface vacantsService;

    @Autowired
    private CategoriesServiceInterface categoriesService;

    @GetMapping("/view/{id}")
    public String showVacant(@PathVariable("id") int idVacant, Model model){
        Vacant vacant = vacantsService.findById(idVacant);
        model.addAttribute("vacant", vacant);
        return "vacants/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int idVacant, Model model){
        System.out.println("Borrando vacante con ID: "+idVacant);
        model.addAttribute("id", idVacant);
        return "vacants/delete";

    }

    @GetMapping("/create")
    public String create(Vacant vacant, Model model){
        model.addAttribute("categories", categoriesService.findAll());
        return "vacants/formVacant";

    }

    @PostMapping("/save")
    public String save(Vacant vacant, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                System.out.println("Error: "+error.getDefaultMessage());
            }
            return "vacants/formVacant";
        }
        vacantsService.save(vacant);
        attributes.addFlashAttribute ("msg", "Registro Guardado");
        System.out.println(vacant);
        return "redirect:/vacants/index";

    }

    @GetMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("vacants", vacantsService.findAll());
        return "vacants/listVacants";
    }

    /**
     * Metodo para realizar una conversion de formato cuando
     * se realiza el Data Binding en el formulario
     *
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
//        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

}
