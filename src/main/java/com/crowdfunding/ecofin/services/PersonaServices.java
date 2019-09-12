package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.configures.MongoConfig;
import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServices {

    @Autowired
    private IPersonaRepository personaRepository;

    public PersonaDTO insertPersona(PersonaDTO persona){
        return  personaRepository.insert(persona);
    }

    public PersonaDTO findById(String id){
        return personaRepository.findById(id);
    }
}
