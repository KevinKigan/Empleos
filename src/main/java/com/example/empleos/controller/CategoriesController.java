package com.example.empleos.controller;

import com.example.empleos.model.Category;
import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.CategoriesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private CategoriesServiceInterface categoriesService;
    private static Logger LOGGER = Logger.getLogger(CategoriesController.class.getName());

    /**
     * Metodo para mostrar todas las categorias
     *
     * @param model
     * @return
     */
    //@GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(Model model){
        model.addAttribute("categories", categoriesService.findAll());
        LOGGER.info("Vista de todas las categorias");
        return "categories/listCategories";
    }

    /**
     * Metodo para mostrar todas las categorias por paginas
     *
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model, Pageable page){
        model.addAttribute("first",categoriesService.findAll(page).isFirst());
        model.addAttribute("last",categoriesService.findAll(page).isLast());
        model.addAttribute("categories", categoriesService.findAll(page));
        return "categories/listCategories";
    }

    /**
     * Metodo para crear una categoria
     *
     * @return
     */
    //@GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("category", new Category());
        return "categories/formCategory";
    }

    /**
     * Metodo para guardar una categoria
     *
     * @param category
     * @param bindingResult
     * @param attributes
     * @return
     */

    //@PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Category category, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                LOGGER.severe("Error: "+error.getDefaultMessage());
            }
            return "categories/formCategory";
        }
        addAtributesMsg(true, "La categoria "+category.getName()+" fue guardada", attributes);
        categoriesService.save(category);
        LOGGER.info("Guardada categoria: ID -> " + category.getId() + " | " + "Nombre -> " + category.getName());
        return "redirect:/categories/index";
    }

    /**
     * Metodo para eliminar una categoria
     *
     * @param idCategory ID de la categoria a eliminar
     * @param attributes
     * @return
     */

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idCategory, RedirectAttributes attributes){
        Category tempCategory = categoriesService.findById(idCategory);
        boolean msgMain;
        String  msgValue;
        try {
            categoriesService.delete(idCategory);
            LOGGER.info("Borrando categoria: ID -> " + tempCategory.getId() + " | " + "Nombre -> " + tempCategory.getName());
            msgMain = true;
            msgValue = "La categoria " + tempCategory.getName() + " fue eliminada";
            addAtributesMsg(msgMain, msgValue, attributes);
        }catch(Exception e){
            msgMain = false;
            msgValue = "La categoria " + tempCategory.getName() + " no pudo ser eliminada por estar siendo usada";
            LOGGER.warning("No se ha podido borrar la categoria: ID -> " + tempCategory.getId() + " | " + "Nombre -> " + tempCategory.getName());
            addAtributesMsg(msgMain, msgValue, attributes);
        }
        return "redirect:/categories/index";
    }

    /**
     * Metodo para editar una categoria
     *
     * @param idCategory ID de la categoria a editar
     * @param model
     * @return
     */

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int idCategory, Model model){
        Category tempCategory = categoriesService.findById(idCategory);
        model.addAttribute("category", tempCategory);
        return "categories/formCategory";
    }


    private void addAtributesMsg(boolean msgMain, String msgValue, RedirectAttributes attributes){
        attributes.addFlashAttribute("msgMain", msgMain);
        attributes.addFlashAttribute("msgValue", msgValue);
    }
}
