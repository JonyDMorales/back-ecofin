package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @PostMapping("/savePersona")
    public Map<String, String> getHome(@Valid PersonaDTO persona, BindingResult status){
        Map<String, String> response = new HashMap<>();
        if (status.hasErrors()) {
            status.getAllErrors().forEach(error -> {
                String key = ((FieldError) error).getField();
                String value = error.getDefaultMessage();
                response.put(key, value);
            });
        } else {
            personaServices.insertPersona(persona);
            response.put("id", persona.getIdString());
        }
        return response;
    }

}
