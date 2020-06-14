package com.example.empleos.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.UsersServiceInterface;
import com.example.empleos.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
	private UsersServiceInterface userService;
	private static Logger LOGGER = Logger.getLogger(UsersController.class.getName());
    
   @GetMapping("/index")
	public String showIndex(Model model) {
    	List<User> list = userService.findAll();
    	model.addAttribute("users", list);
		return "users/listUsers";
	}
    
    @GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		User tempUser = userService.findById(idUser);
		LOGGER.info("Borrando vacante: ID -> "+ tempUser.getId()+" | "+ "Nombre -> " +tempUser.getName());
		userService.delete(idUser);
		attributes.addFlashAttribute("msg", "El usuario " + tempUser.getName() + " fue eliminado!.");
		return "redirect:/users/index";
	}

	/**
	 * Metodo para crear una categoria
	 *
	 * @return
	 */
	@GetMapping("/create")
	public String create(Model model){
		model.addAttribute("user", new User());
		return "users/formSign";
	}

	/**
	 * Metodo para crear un usuario
	 *
	 * @param user
	 * @param bindingResult
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String save(User user, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOGGER.severe("Error: " + error.getDefaultMessage());
			}
			return "users/formSign";
		}
		user.setStatus(1);
		user.setRegistrationDate(new Date());
		userService.save(user);
		attributes.addFlashAttribute("msg", "El usuario " + user.getName() + " fue creado");
		LOGGER.info(user.toString());
		return "redirect:/home";
	}

	/**
	 * Metodo para editar un usuario
	 *
	 * @param idUser ID del usuario a editar
	 * @param model
	 * @return
	 */

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idUser, Model model){
		User tempUser = userService.findById(idUser);
		model.addAttribute("user", tempUser);
		return "users/formSign";
	}
}
