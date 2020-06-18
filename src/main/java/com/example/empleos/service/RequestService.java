package com.example.empleos.service;

import java.util.List;

import com.example.empleos.model.Request;
import com.example.empleos.repository.CategoriesRepository;
import com.example.empleos.repository.RequestRepository;
import com.example.empleos.repository.UsersRepository;
import com.example.empleos.repository.VacantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements RequestServiceInterface {

	@Autowired
	private RequestRepository requestsRepo;
	@Autowired
	private VacantsRepository vacantsRepo;
	@Autowired
	private UsersRepository usersRepo;
	@Override
	public void save(Request request) {
		requestsRepo.save(request);
	}

	@Override
	public void delete(Integer idRequest) {
		requestsRepo.delete(requestsRepo.findById(idRequest).get());
	}

	@Override
	public List<Request> findAll() {
		return requestsRepo.findAll();
	}

	@Override
	public Request findById(Integer idRequest) {
		return requestsRepo.findById(idRequest).get();
	}

	@Override
	public Page<Request> findAll(Pageable page) {
		return requestsRepo.findAll(page);
	}

	@Override
	public Page<Request> findAllUser(Pageable page, int idUser) {
		return requestsRepo.findByUser(page, usersRepo.findById(idUser).get());
	}

	@Override
	public boolean findByVacantAndUser(int idVacant, int idUser) {
		Request request = requestsRepo.findByVacantAndUser(vacantsRepo.findById(idVacant).get(), usersRepo.findById(idUser).get());
		if(request!=null)return true;
		else return false;
	}
}
