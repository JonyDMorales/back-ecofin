package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import com.crowdfunding.ecofin.services.ProyectoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/admin/delete/fisico/persona")
    public void deletePersona(@RequestBody Map<String,String> id){
        if(id != null && id.get("id") != null) {
            personaServices.deletePersona(id.get("id"));
        }

    }

    @DeleteMapping("/admin/delete/fisico/proyecto")
    public void deleteProyecto(@RequestBody ProyectoDTO proyectoDTO){
        if(proyectoDTO != null){
            proyectoServices.deleteProyecto(proyectoDTO.getId());
        }
    }
}
