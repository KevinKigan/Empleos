package com.example.empleos.controller;

import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.CategoriesServiceInterface;
import com.example.empleos.service.VacantsServiceInterface;
import com.example.empleos.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/vacants")
public class VancantsController {

    @Value("${empleos.pathImages}")
    private String pathImages;

    @Autowired
    private VacantsServiceInterface vacantsService;

    @Autowired
    private CategoriesServiceInterface categoriesService;

    private static Logger LOGGER = Logger.getLogger(VancantsController.class.getName());

    /**
     * Metodo para mostrar los detalles de una vacante
     *
     * @param idVacant
     * @param model
     * @return
     */
    @GetMapping("/view/{id}")
    public String showVacant(@PathVariable("id") int idVacant, Model model){
        Vacant vacant = vacantsService.findById(idVacant);
        model.addAttribute("vacant", vacant);
        return "vacants/detail";
    }

    /**
     * Metodo para editar una vacante
     *
     * @param idVacant ID de la vacante a editar
     * @param model
     * @return
     */

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int idVacant, Model model){
        Vacant tempVacant = vacantsService.findById(idVacant);
        model.addAttribute("vacant", tempVacant);
        return "vacants/formVacant";
    }

    /**
     * Metodo para eliminar una vacante
     *
     * @param idVacant
     * @param attributes
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int idVacant, RedirectAttributes attributes){
        Vacant tempVacant = vacantsService.findById(idVacant);
        LOGGER.info("Borrando vacante: ID -> "+ tempVacant.getId()+" | "+ "Nombre -> " +tempVacant.getName());
        vacantsService.delete(idVacant);
        attributes.addFlashAttribute ("msg", "La vacante "+tempVacant.getName()+" fue eliminada");
        return "redirect:/vacants/index";

    }

    /**
     * Mertodo para crear una vacante
     *
     * @param vacant
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("vacant", new Vacant());
        return "vacants/formVacant";

    }

    /**
     * Metodo para crear una vacante
     *
     * @param vacant
     * @param multipartFile Fichero de imagen
     * @param bindingResult
     * @param attributes
     * @return
     */
    @PostMapping("/save")
    public String save(Vacant vacant, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile multipartFile, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                LOGGER.severe("Error: "+error.getDefaultMessage());
            }
            return "vacants/formVacant";
        }
        if(!multipartFile.isEmpty()){
            String imageName = UploadFiles.saveFile(multipartFile, pathImages);
            if(imageName!=null){
                vacant.setImage(imageName);
            }
        }
        vacantsService.save(vacant);
        attributes.addFlashAttribute ("msg", "La vacante "+vacant.getName()+" fue actualizada");

        LOGGER.info(vacant.toString());
        return "redirect:/vacants/index";

    }

    /**
     * Metodo para mostrar todas las vacantes
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("vacants", vacantsService.findAll());
        return "vacants/listVacants";
    }

    /**
     * Metodo para mostrar todas las vacantes por paginas
     *
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/indexPaginate")
    public String showIndexPaginate(Model model, Pageable page){
        model.addAttribute("vacants", vacantsService.findAll(page));
        return "vacants/listVacants";
    }

    /**
     * Metodo para añadir al modelo la lista de categorias
     *
     * @param model
     */
    @ModelAttribute
    public void setGenerics(Model model){
        model.addAttribute("categories", categoriesService.findAll());
    }

    /**
     * Metodo para realizar una conversion de formato cuando
     * se realiza el Data Binding en el formulario
     *
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

}
