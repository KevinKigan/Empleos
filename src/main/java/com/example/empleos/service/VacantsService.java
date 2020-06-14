package com.example.empleos.service;

import com.example.empleos.model.Vacant;
import com.example.empleos.repository.VacantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacantsService implements VacantsServiceInterface {

    @Autowired
    private VacantsRepository vacantsRepo;

    public VacantsService() {}

    /**
     * Metodo para buscar todas las vacantes
     *
     * @return vacantes
     */

    @Override
    public List<Vacant> findAll() {
        return vacantsRepo.findAll();
    }

    /**
     * Metodo para retornar una vacante de la lista de empleos
     * @param id Numero de identificacion de la vancante a buscar
     * @return Vacante encontrada
     */

    @Override
    public Vacant findById(Integer id) {
        Optional<Vacant> optional = vacantsRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * Metodo para guardar la vacante
     *
     * @param vacant Vacante a guardar
     */

    @Override
    public void save(Vacant vacant) {
        vacantsRepo.save(vacant);
    }

    /**
     * Metodo para buscar las vacantes destacadas
     *
     * @return Lista de vacantes destacadas
     */

    @Override
    public List<Vacant> findOutstanding() {
        return vacantsRepo.findByStatusAndOutstandingOrderByIdDesc("Aprobada", true);
    }
}
