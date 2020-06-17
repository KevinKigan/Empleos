package com.example.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.example.empleos.model.Category;
import com.example.empleos.model.Request;
import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.service.RequestService;
import com.example.empleos.service.RequestServiceInterface;
import com.example.empleos.service.UsersServiceInterface;
import com.example.empleos.service.VacantsServiceInterface;
import com.example.empleos.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	private RequestServiceInterface requestService;
	@Autowired
	private UsersServiceInterface userService;
	@Autowired
	private VacantsServiceInterface vacantsService;
	@Value("${empleos.pathCV}")
	private String path;
	private static Logger LOGGER = Logger.getLogger(RequestController.class.getName());
	/**
	 * EJERCICIO: Declarar esta propiedad en el archivo application.properties. El valor sera el directorio
	 * en donde se guardarán los archivos de los Curriculums Vitaes de los usuarios.
	 */
//	@Value("${empleosapp.ruta.cv}")
//	private String ruta;
		
    /**
	 * Metodo que muestra la lista de solicitudes sin paginacion
	 * Seguridad: Solo disponible para un usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
    @GetMapping("/index") 
	public String showIndex() {

    	// EJERCICIO
		return "requests/listRequests";
		
	}
    
    /**
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String showIndexPaginate() {
		
		// EJERCICIO
		return "requests/listRequests";
		
	}
    
	/**
	 * Método para renderizar el formulario para solicitar una Vacante
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@GetMapping("/create/{idVacant}")
	public String create(@PathVariable("idVacant") int idVacant, Model model) {
		model.addAttribute("vacant",vacantsService.findById(idVacant));
		return "requests/formRequest";
		
	}
	
	/**
	 * Método que guarda la solicitud enviada por el usuario en la base de datos
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@PostMapping("/save")
	public String save(Request request, BindingResult bindingResult, RedirectAttributes attributes, @RequestParam("fileCV") MultipartFile multiPart, Authentication auth){
		String username = auth.getName();
		if(bindingResult.hasErrors()){
			for (ObjectError error: bindingResult.getAllErrors()){
				LOGGER.severe("Error: "+error.getDefaultMessage());
			}
			return "requests/formRequest";
		}

		if (!multiPart.isEmpty()) {
			String fileName = UploadFiles.saveFile(multiPart, path);
			if (fileName!=null){
				request.setFile(fileName);
			}
		}
		// Buscamos el objeto Usuario en BD
		User user = userService.findByUsername(username);

		request.setUser(user);
		request.setDate(new Date());
		requestService.save(request);
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
		requestService.save(request);
		return "redirect:/home";
	}

	/**
	 * Método para eliminar una solicitud
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR. 
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String delete() {
		
		// EJERCICIO
		return "redirect:/requests/indexPaginate";
		
	}

	@ModelAttribute // Model Attribute añade al modelo atributos que pueden ser utilizados por el controlador
	public void setGenerics(Model model){
		model.addAttribute("request", new Request());
	}

	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
