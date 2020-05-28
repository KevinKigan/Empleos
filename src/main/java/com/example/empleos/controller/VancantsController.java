package com.example.empleos.controller;

import com.example.empleos.model.Vacant;
import com.example.empleos.service.VacantsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
