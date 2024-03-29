package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import com.crowdfunding.ecofin.services.ProyectoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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

    @PostMapping("/admin/get/all/proyectos")
    public List<ProyectoDTO> getAllProyecto(){ return proyectoServices.adminConsultAllProyects(); }

    @PostMapping("/admin/save/persona")
    public Map<String, String> savePersona(@Valid @RequestBody PersonaDTO persona, BindingResult status){
        Map<String, String> response = new HashMap<>();
        if (status.hasErrors()) {
            status.getAllErrors().forEach(error -> {
                String key = ((FieldError) error).getField();
                String value = error.getDefaultMessage();
                response.put(key, value);
            });
        } else {
            List<String> perfiles = persona.getPerfil();
            perfiles.add("ROLE_ADMIN");
            personaServices.savePersona(persona);
            response.put("id", persona.getId());
        }
        return response;
    }
}
