package com.example.empleos.service;

import java.util.List;

import com.example.empleos.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestServiceInterface {

	void save(Request request);
	void delete(Integer idRequest);
	List<Request> findAll();
	Request findById(Integer idRequest);
	Page<Request> findAll(Pageable page);
	Page<Request> findAllUser(Pageable page, int idUser);
	boolean findByVacantAndUser(int idVacant, int idUser);
}
