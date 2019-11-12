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
public class ProyectoController {

    @Autowired
    private ProyectoServices proyectoServices;

    @PostMapping("/save/proyecto")
    public Map<String, String> saveProyecto(@Valid @RequestBody ProyectoDTO proyectoDTO, BindingResult status){
        Map<String, String> response = new HashMap<>();
        if (status.hasErrors()) {
            status.getAllErrors().forEach(error -> {
                String key = ((FieldError) error).getField();
                String value = error.getDefaultMessage();
                response.put(key, value);
            });
        } else {
            if(proyectoServices.insertPropyecto(proyectoDTO) != null) {
                response.put("id", proyectoDTO.getId());
            }
        }
        return response;
    }

    @PutMapping("/update/proyecto")
    public ProyectoDTO updateProyecto(@Valid @RequestBody ProyectoDTO proyectoDTO){
        return proyectoServices.updateProyecto(proyectoDTO);
    }

    @DeleteMapping("/delete/logico/proyecto")
    public void deleteProyecto(@RequestBody ProyectoDTO proyectoDTO){
        if(proyectoDTO != null && proyectoDTO.getId() != null){
            proyectoDTO.setActivo(0);
            proyectoServices.updateProyecto(proyectoDTO);
        }
    }

    @PostMapping("/get/proyecto")
    public  ProyectoDTO getProyecto(@RequestBody ProyectoDTO proyectoDTO){
        if(proyectoDTO != null){
            return proyectoServices.findById(proyectoDTO.getId());
        }
        return null;
    }

    @PostMapping("/get/proyectos")
    public List<ProyectoDTO> getProyects(@RequestBody PersonaDTO personaDTO){
        if(personaDTO != null){
            return proyectoServices.getProyects(personaDTO.getId());
        }
        return null;
    }

    @PostMapping("/get/all/proyectos")
    public List<ProyectoDTO> getAllProyecto(){ return proyectoServices.consultAllProyects(); }

}
