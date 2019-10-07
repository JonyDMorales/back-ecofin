package com.crowdfunding.ecofin.controllers;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.services.PersonaServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @PostMapping("/save/persona")
    public Map<String, String> savePersona(@Valid @RequestBody PersonaDTO persona, BindingResult status){
        Map<String, String> response = new HashMap<>();
        if (status.hasErrors()) {
            status.getAllErrors().forEach(error -> {
                String key = ((FieldError) error).getField();
                String value = error.getDefaultMessage();
                response.put(key, value);
            });
        } else {
            personaServices.savePersona(persona);
            response.put("id", persona.getId());
        }
        return response;
    }

    @PutMapping("/update/persona")
    public PersonaDTO updatePersona(@Valid @RequestBody PersonaDTO persona){
        return personaServices.updatePersona(persona);
    }

    @DeleteMapping("/delete/persona")
    public void deletePersona(@RequestBody PersonaDTO personaDTO){
        if(personaDTO != null) {
            personaServices.deletePersona(personaDTO.getId());
        }

    }

    @PostMapping("/get/persona")
    public PersonaDTO getPersona(@RequestBody PersonaDTO personaDTO){
        if(personaDTO != null){
            return personaServices.findById(personaDTO.getId());
        }
        return  null;
    }

    @PostMapping("/get/all/persona")
    public List<PersonaDTO> getAllPersona(){
        return personaServices.consultAllPersonas();
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody PersonaDTO personaDTO){
        if(personaDTO != null){
            return personaServices.login(personaDTO.getEmail(), personaDTO.getPassword());
        }
        return  new HashMap<>();
    }
}
