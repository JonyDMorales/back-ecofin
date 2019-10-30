package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import com.crowdfunding.ecofin.services.ProyectoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController("/admin")
public class AdminController {

    @Autowired
    private PersonaServices personaServices;
    @Autowired
    private ProyectoServices proyectoServices;

    @PostMapping("/get/all/persona")
    public List<PersonaDTO> getAllPersona(){
        return personaServices.consultAllPersonas();
    }
}
