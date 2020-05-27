package com.example.empleos.controller;

import com.example.empleos.model.Vacant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

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
        List<Vacant> list = getVacants();
        model.addAttribute("vacants", list);
        return "tableDetailsVacants";
    }



        private List<Vacant> getVacants(){
        List<Vacant> list = new LinkedList<>();

        // Creamos oferta de trabajo 1
        Vacant vacant_1 = new Vacant();
        vacant_1.setId(1);
        vacant_1.setName("Ingeniero Civil");
        vacant_1.setDescription("Solicitamos ingeniero civil para diseñar puente peatonal");
        vacant_1.setSalary(8500.0);
        vacant_1.setOutstanding(true);
        vacant_1.setImage("empresa1.png");

        // Creamos oferta de trabajo 2
        Vacant vacant_2 = new Vacant();
        vacant_2.setId(2);
        vacant_2.setName("Contador publico");
        vacant_2.setDescription("Empresa importante solicita contador con 5 años de experiencia titulado");
        vacant_2.setSalary(12000.0);
        vacant_2.setOutstanding(false);
        vacant_2.setImage("empresa2.png");
        // Creamos oferta de trabajo 3
        Vacant vacant_3 = new Vacant();
        vacant_3.setId(3);
        vacant_3.setName("Ingeniero Electrico");
        vacant_3.setDescription("Solicitamos ingeniero electrico para mantener una instalacion electrica");
        vacant_3.setSalary(10500.0);
        vacant_3.setOutstanding(false);

        // Creamos oferta de trabajo 4
        Vacant vacant_4 = new Vacant();
        vacant_4.setId(4);
        vacant_4.setName("Diseñador Gráfico");
        vacant_4.setDescription("Solicitamos diseñador grafico titulado para diseñar publicidad de la empresa");
        vacant_4.setSalary(7500.0);
        vacant_4.setOutstanding(true);

        list.add(vacant_1);
        list.add(vacant_2);
        list.add(vacant_3);
        list.add(vacant_4);
        return list;

    }
}
