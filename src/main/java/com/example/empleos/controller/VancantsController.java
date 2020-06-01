package com.example.empleos.controller;

import com.example.empleos.model.Vacant;
import com.example.empleos.service.VacantsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacants")
public class VancantsController {

    @Autowired
    private VacantsServiceInterface vacantsService;

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
    public String create(Model model){

        return "vacants/formVacant";

    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,                @RequestParam("description") String description,
                       @RequestParam("state") String state,              @RequestParam("date") String date,
                       @RequestParam("outstanding") Boolean outstanding, @RequestParam("salary") double salary,
                       @RequestParam("details") String details){
        System.out.println("hola");

        return "vacants/listVacants";

    }
}
