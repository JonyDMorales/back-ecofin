package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.configures.MongoConfig;
import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServices {

    @Autowired
    private IPersonaRepository personaRepository;

    public PersonaDTO savePersona(PersonaDTO persona){ return  personaRepository.insert(persona); }

    public PersonaDTO findById(String id){
        return personaRepository.findById(id);
    }

    public PersonaDTO login(String email, String password){
        return personaRepository.findByEmailAndPassword(email, password);
    }

    public PersonaDTO updatePersona(PersonaDTO persona){
        if(!persona.getId().isEmpty()){
            PersonaDTO actual = personaRepository.findById(persona.getId());

            actual.setNombre(persona.getNombre());
            actual.setEmail(persona.getEmail());
            actual.setPerfil(persona.getPerfil());
            actual.setPassword(persona.getPassword());
            actual.setDireccion(persona.getDireccion());
            actual.setProfesion(persona.getProfesion());
            actual.setFechaNacimiento(persona.getFechaNacimiento());
            actual.setSexo(persona.getSexo());
            actual.setPais(persona.getPais());
            actual.setActivo(persona.getActivo());
            actual.setProyectos(persona.getProyectos());

            return personaRepository.save(persona);
        }
        return null;
    }

    public void deletePersona(String id){
        personaRepository.deleteById(id);
    }

    public List<PersonaDTO> consultAllPersonas(){
        return personaRepository.findAll();
    }

}
