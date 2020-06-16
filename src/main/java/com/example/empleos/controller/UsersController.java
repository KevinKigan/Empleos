package com.example.empleos.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.example.empleos.model.Profile;
import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.UsersServiceInterface;
import com.example.empleos.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	/**
	 * Metodo para mostrar todas los usuarios por paginas
	 *
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String showIndexPaginate(Model model, Pageable page){
		model.addAttribute("first",userService.findAll(page).isFirst());
		model.addAttribute("last",userService.findAll(page).isLast());
		model.addAttribute("users", userService.findAll(page));
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
	 * Metodo para crear un usuario
	 *
	 * @param user
	 * @param bindingResult
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String save(User user, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOGGER.severe("Error: " + error.getDefaultMessage());
			}
			return "users/formSign";
		}
		user.setStatus(1);
		user.setRegistrationDate(new Date());
		Profile profile = new Profile();
		profile.setId(3); // Perfil USUARIO
		user.addProfile(profile);

		try {
			userService.save(user);
			attributes.addFlashAttribute("msg", "El usuario " + user.getName() + " fue guardado correctamente");
			LOGGER.info(user.toString());
		}catch(Exception e){
			model.addAttribute("msg", "No se ha podido guardar al usuario "+user.getName());
			LOGGER.severe("No se ha podido guardar al usuario "+user.getName());
			return "users/formSign";
		}


		return "redirect:/users/index";
	}

	/**
	 * Metodo para editar un usuario
	 *
	 * @param idUser ID del usuario a editar
	 * @param
	 * @return
	 */

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idUser, Model model){
		User tempUser = userService.findById(idUser);
		model.addAttribute("user", tempUser);
		model.addAttribute("edit", true);
		return "users/formSign";
	}

	/**
	 * Metodo para bloquear o desbloquear un usuario
	 *
	 * @param idUser ID del usuario a bloquear o desbloquear
	 * @param
	 * @return
	 */

	@GetMapping("/blockUnblock/{id}")
	public String blockUnblock(@PathVariable("id") int idUser, Model model){
		userService.blockUnblock(idUser);
		return "redirect:/users/index";
	}

	@ModelAttribute // Model Attribute añade al modelo atributos que pueden ser utilizados por el controlador
	public void setGenerics(Model model){
		User user = new User();
		model.addAttribute("user", user);
	}
	@GetMapping("/")
	public String error(Model model) {
		return "redirect:/home";
	}


}
