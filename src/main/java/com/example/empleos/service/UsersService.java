package com.example.empleos.service;

import com.example.empleos.model.User;
import com.example.empleos.model.Vacant;
import com.example.empleos.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsersService implements UsersServiceInterface {

	@Autowired
	private UsersRepository usersRepo;

	/**
	 * Metodo para guardar un usuario
	 *
	 * @param user Usuario a guardar
	 */
	public void save(User user) {
		usersRepo.save(user);
	}

	/**
	 * Metodo para borrar un usuario
	 *
	 * @param idUser Id del usuario a eliminar
	 */
	@Override
	public void delete(int idUser) {
		usersRepo.deleteById(idUser);
	}

	/**
	 * Metodo para buscar todos los usuarios
	 *
	 * @return
	 */
	public List<User> findAll() {
		return usersRepo.findAll();
	}

	/**
	 * Metodo para retornar un usuario de la lista
	 * @param id Numero de identificacion del usuario a buscar
	 * @return Usuario encontrada
	 */

	@Override
	public User findById(Integer id) {
		Optional<User> optional = usersRepo.findById(id);
		if(optional.isPresent()){
			return optional.get();
		}
		return null;
	}
}
