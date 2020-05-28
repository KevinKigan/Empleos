package com.example.empleos.service;

import com.example.empleos.model.Vacant;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VacantsService implements VacantsServiceInterface {

    private List<Vacant> list = null;

    public VacantsService() {
        list = new LinkedList<>();

        // Creamos oferta de trabajo 1
        Vacant vacant_1 = new Vacant();
        vacant_1.setId(1);
        vacant_1.setName("Ingeniero Civil");
        vacant_1.setDescription("Solicitamos ingeniero civil para diseñar puente peatonal");
        vacant_1.setSalary(8500.0);
        vacant_1.setOutstanding(true);
        vacant_1.setImage("empresa1.png");

        // Creamos oferta de trabajo 2
        Vacant vacant_2 = new Vacant();
        vacant_2.setId(2);
        vacant_2.setName("Contador publico");
        vacant_2.setDescription("Empresa importante solicita contador con 5 años de experiencia titulado");
        vacant_2.setSalary(12000.0);
        vacant_2.setOutstanding(false);
        vacant_2.setImage("empresa2.png");
        // Creamos oferta de trabajo 3
        Vacant vacant_3 = new Vacant();
        vacant_3.setId(3);
        vacant_3.setName("Ingeniero Electrico");
        vacant_3.setDescription("Solicitamos ingeniero electrico para mantener una instalacion electrica");
        vacant_3.setSalary(10500.0);
        vacant_3.setOutstanding(false);

        // Creamos oferta de trabajo 4
        Vacant vacant_4 = new Vacant();
        vacant_4.setId(4);
        vacant_4.setName("Diseñador Gráfico");
        vacant_4.setDescription("Solicitamos diseñador grafico titulado para diseñar publicidad de la empresa");
        vacant_4.setSalary(7500.0);
        vacant_4.setOutstanding(true);

        list.add(vacant_1);
        list.add(vacant_2);
        list.add(vacant_3);
        list.add(vacant_4);
    }

    /**
     * Metodo para buscar todas las vacantes
     *
     * @return vacantes
     */

    @Override
    public List<Vacant> findAll() {
        return list;
    }

    /**
     * Metodo para retornar una vacante de la lista de empleos
     * @param id Numero de identificacion de la vancante a buscar
     * @return Vacante encontrada
     */

    @Override
    public Vacant findById(Integer id) {
        try {
            return list.stream().filter(vac -> vac.getId() == id).findFirst().get();
        }catch (Exception e) {
            return null;
        }
    }
}
