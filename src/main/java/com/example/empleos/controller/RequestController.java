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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * Seguridad: Solo disponible para usuarios.
	 * @return
	 */
	@GetMapping("/indexPaginateUser")
	public String showIndexPaginateUser(Authentication auth, Model model, Pageable pageable) {
		User user = userService.findByUsername(auth.getName());
		Page<Request> page = (requestService.findAllUser(pageable, user.getId()));
		model.addAttribute("requestsUser", page);
		model.addAttribute("first",page.isFirst());
		model.addAttribute("last",page.isLast());
		return "requests/listRequestsUser";

	}
	/**
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String showIndexPaginate(Authentication auth, Model model, Pageable pageable) {
		Page<Request> page = (requestService.findAll(pageable));
		model.addAttribute("requests", page);
		model.addAttribute("first",page.isFirst());
		model.addAttribute("last",page.isLast());
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
	public String save(Request request, BindingResult bindingResult, RedirectAttributes attributes, @RequestParam("file") MultipartFile multiPart, Authentication auth){

		String username = auth.getName();
		if (multiPart.getName()!="") {
			String fileName = UploadFiles.saveFile(multiPart, path);
			if (fileName!=null){
				request.setFile(fileName);
			}
		}
		User user = userService.findByUsername(username);
		request.setUser(user);
		request.setDate(new Date());
		requestService.save(request);
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
		return "redirect:/home";
	}

	/**
	 * Método para eliminar una solicitud
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idRequest, RedirectAttributes attributes) {
		Request tempRequest = requestService.findById(idRequest);
		LOGGER.info("Borrando solicitud: ID -> "+ tempRequest.getId()+" | "+ "Nombre de la vacante -> " +tempRequest.getVacant().getName());
		requestService.delete(idRequest);
		attributes.addFlashAttribute ("msg", "La solicitud "+tempRequest.getVacant().getName()+" fue eliminada");
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
