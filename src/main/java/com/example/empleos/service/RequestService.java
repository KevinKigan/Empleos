package com.example.empleos.service;

import java.util.List;

import com.example.empleos.model.Request;
import com.example.empleos.repository.CategoriesRepository;
import com.example.empleos.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements RequestServiceInterface {

	@Autowired
	private RequestRepository requestsRepo;

	@Override
	public void save(Request request) {

	}

	@Override
	public void eliminar(Integer idRequest) {

	}

	@Override
	public List<Request> findAll() {
		return null;
	}

	@Override
	public Request findById(Integer idRequest) {
		return null;
	}

	@Override
	public Page<Request> findAll(Pageable page) {
		return null;
	}
}
