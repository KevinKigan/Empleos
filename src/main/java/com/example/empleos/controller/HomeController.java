package com.example.empleos.controller;

import com.example.empleos.model.Vacant;
import com.example.empleos.service.VacantsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private VacantsServiceInterface vacantsService;

    /**
     * Metodo para mostrar la pagina de inicio
     *
     * @param model Modelo para la vista
     * @return Nombre del archivo html
     */
    @GetMapping("/")
    public String showHome(Model model) {
        String name = "Auxiliar de Contabilidad";
        Date pubDate = new Date();
        double salary = 9000.0;
        boolean valid = true;
        model.addAttribute("name", name);
        model.addAttribute("date", pubDate);
        model.addAttribute("salary", salary);
        model.addAttribute("valid", valid);
        return "home";
    }


    /**
     * Metodo para mostrar el listado de ofertas de empleo
     *
     * @param model Modelo para la vista
     * @return Nombre del archivo html
     */
    @GetMapping("/list")
    public String showList(Model model) {
        List<String> list = new LinkedList<>();
        list.add("Ingeniero de Sistemas");
        list.add("Auxiliar de Contabilidad");
        list.add("Vendedor");
        list.add("Arquitecto");
        model.addAttribute("empleos", list);
        return "list";
    }

    /**
     * Metodo para mostrar los detalles de una vacante
     *
     * @param model Modelo para la vista
     * @return Nombre del archivo html
     */
    @GetMapping("/detail")
    public String showDetailVacant(Model model) {
        Vacant vacant = new Vacant();
        vacant.setName("Ingeniero de Comunicaciones");
        vacant.setDescription("Se solicita ingeniero para dar soporte a intranet");
        vacant.setSalary(9700.0);
        model.addAttribute("vacant", vacant);
        return "detailVacant";
    }

    /**
     * Metodo para mostrar los detalles de una vacante
     *
     * @param model Modelo para la vista
     * @return Nombre del archivo html
     */
    @GetMapping("/detailsVancants")
    public String showTableDetailsVacants(Model model) {

        List<Vacant> list = vacantsService.findAll();
        model.addAttribute("vacants", list);
        return "tableDetailsVacants";
    }
}
