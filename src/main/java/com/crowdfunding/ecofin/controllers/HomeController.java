package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.services.MongoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private MongoServices mongoServices;

    @PostMapping("/savePersona")
    public PersonaDTO getHome(@RequestParam("nombre") String nombre,
                              @RequestParam("direccion") String direccion,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("perfil") String perfil,
                              @RequestParam("profesion") String profesion){

        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNombre(nombre);
        personaDTO.setDireccion(direccion);
        personaDTO.setEmail(email);
        personaDTO.setPassword(password);
        personaDTO.setPerfil(perfil);
        personaDTO.setProfesion(profesion);

        return mongoServices.insertPersona(personaDTO);
    }

}
