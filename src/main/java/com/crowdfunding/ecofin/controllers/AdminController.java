package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import com.crowdfunding.ecofin.services.ProyectoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminController {

    @Autowired
    private PersonaServices personaServices;
    @Autowired
    private ProyectoServices proyectoServices;

    @PostMapping("/admin/get/all/persona")
    public List<PersonaDTO> getAllPersona(){
        return personaServices.consultAllPersonas();
    }

    @PostMapping("/admin/get/all/proyecto")
    public List<ProyectoDTO> getAllProyecto(){ return proyectoServices.consultAllProyects(); }
}
