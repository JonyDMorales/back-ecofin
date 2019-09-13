package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.configures.MongoConfig;
import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServices {

    @Autowired
    private IPersonaRepository personaRepository;

    public PersonaDTO savePersona(PersonaDTO persona){ return  personaRepository.insert(persona); }

    public PersonaDTO findById(String id){
        return personaRepository.findById(id);
    }

    public PersonaDTO updatePersona(PersonaDTO persona){
        if(!persona.getIdString().isEmpty()){
            PersonaDTO actual = personaRepository.findById(persona.getIdString());

            actual.setNombre(persona.getNombre());
            actual.setEmail(persona.getEmail());
            actual.setPerfil(persona.getPerfil());
            actual.setPassword(persona.getPassword());
            actual.setDireccion(persona.getDireccion());
            actual.setProfesion(persona.getProfesion());
            actual.setFechaNacimiento(persona.getFechaNacimiento());
            actual.setSexo(persona.getSexo());
            actual.setPais(persona.getPais());
            actual.setStatus(persona.isStatus());

            return personaRepository.save(actual);
        }
        return null;
    }

    public void deletePersona(ObjectId id){
        personaRepository.deleteById(id);
    }

}
