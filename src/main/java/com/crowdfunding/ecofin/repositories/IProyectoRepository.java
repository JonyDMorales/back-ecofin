package com.crowdfunding.ecofin.repositories;

import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IProyectoRepository extends MongoRepository<ProyectoDTO, ObjectId> {

    public ProyectoDTO findById(String id);
    public  void deleteById(String id);
    @Query("{'activo': 0}")
    public  List<ProyectoDTO> findAll();
    @Query("{'activo': 1}")
    public  List<ProyectoDTO> findAllByOrderByPrioridadDesc();
    @Query("{'propietario.id': ?0}")
    public List<ProyectoDTO> findByPropietario(String id);
}
