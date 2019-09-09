package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.configures.MongoConfig;
import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServices {

    @Autowired
    private IPersonaRepository mongo;

    public PersonaDTO insertPersona(PersonaDTO persona){
        return  mongo.insert(persona);
    }
}
