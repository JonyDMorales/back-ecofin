package com.crowdfunding.ecofin.configures;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoConfig extends MongoRepository<PersonaDTO, ObjectId> {



}
