package com.example.empleos.service;

import java.util.List;

import com.example.empleos.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestServiceInterface {

	void save(Request request);
	
	// EJERCICIO: Método que elimina una Solicitud de la BD (solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR).
	void delete(Integer idRequest);
	
	// EJERCICIO: Método que recupera todas las Solicitudes guardadas en la BD (solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR).
	List<Request> findAll();
	
	// EJERCICIO: Método que busca una Solicitud en la BD (solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR).
	Request findById(Integer idRequest);
	
	// EJERCICIO: Método que recupera todas las Solicitudes (con paginación) guardadas en la BD (solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR).
	Page<Request> findAll(Pageable page);
}
