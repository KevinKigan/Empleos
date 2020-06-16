package com.example.empleos.controller;

import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.CategoriesServiceInterface;
import com.example.empleos.service.UsersServiceInterface;
import com.example.empleos.service.VacantsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {

    @Autowired
    private VacantsServiceInterface vacantsService;
    @Autowired
    private CategoriesServiceInterface categoriesService;
    @Autowired
    private UsersServiceInterface userService;
    private static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    /**
     * Metodo para mostrar la pagina de inicio
     *
     * @param model Modelo para la vista
     * @return Nombre del archivo html
     */
    @GetMapping("/home")
    public String showHome(Model model) {
        return "home";
    }

    /**
     * Metodo para hacer login como usuario registrado anteriormente
     *
     * @param auth
     * @return
     */
    @GetMapping("/index")
    public String showIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();
        auth.getAuthorities().stream().forEach(rol-> System.out.println("User: "+username+" ROL -> "+rol.getAuthority()));
        System.out.println(username);
        //HttpSesion para almacenar satos en la sesion del usuario
        if(session.getAttribute("user")==null) {
            User user = userService.findByUsername(username);
            user.setPassword(null);
            session.setAttribute("user", user);
        }
        return "redirect:/home";
    }

    @ModelAttribute // Model Attribute añade al modelo atributos que pueden ser utilizados por el controlador
    public void setGenerics(Model model){
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("vacantsOutstanding", vacantsService.findOutstanding(true));
        model.addAttribute("vacantsNotOutstanding", vacantsService.findOutstanding(false));
        Vacant vacantSearch = new Vacant();
        vacantSearch.reset();
        model.addAttribute("search", vacantSearch);
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

    @GetMapping("/")
    public String error(Model model) {
        return "redirect:/home";
    }

    /**
     * Metodo para buscar vacantes
     *
     * @param vacant
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String search(@ModelAttribute("search") Vacant vacant, Model model){
        LOGGER.info("Buscando "+ vacant.toString());
        ExampleMatcher matcher = ExampleMatcher.matching()
                // where description like '%?%'
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
        // Buscamos primero las destacadas pero se buscan todas
        vacant.setOutstanding(true);
        model.addAttribute("vacantsOutstanding", vacantsService.findByExample(Example.of(vacant, matcher)));
        vacant.setOutstanding(false);
        model.addAttribute("vacantsNotOutstanding", vacantsService.findByExample(Example.of(vacant, matcher)));
        return "home";
    }

    /**
     * Metodo para crear un usuario
     *
     * @return
     */
    @GetMapping("/signup")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "users/formSign";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
