package com.crowdfunding.ecofin.repositories;

import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProyectoRepository extends MongoRepository<ProyectoDTO, ObjectId> {

    public ProyectoDTO findById(String id);
    public  void deleteById(String id);
}
