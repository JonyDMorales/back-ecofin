package com.crowdfunding.ecofin.repositories;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPersonaRepository extends MongoRepository<PersonaDTO, ObjectId> {
    public PersonaDTO findById(String id);
    public void deleteById(String id);
    public PersonaDTO findByEmail(String email);

}
