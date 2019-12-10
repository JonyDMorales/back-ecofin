package com.crowdfunding.ecofin.services;


import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaServices {

    @Autowired
    private IPersonaRepository personaRepository;

    @Value("${jwt.secret}")
    private  String secretKey;

    //5 * 60 * 1000 (5 minutos)
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    private String passwordKey = "EcoPassword00000000000000000000000000000000000000000000000000000";

    private String getJWTToken(String id, String username, List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(String rol :roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(rol));
        }

        String token = Jwts.builder().setId(id).setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return token;
    }

    private String decryptPassword(String cifrado){
        Claims claims = Jwts.parser().setSigningKey(passwordKey.getBytes()).parseClaimsJws(cifrado).getBody();
        String pass = (String) claims.get("password");
        return pass;
    }

    public PersonaDTO savePersona(PersonaDTO persona){ return  personaRepository.insert(persona); }

    public PersonaDTO findById(String id){
        if(id != null && !id.isEmpty())
            return personaRepository.findById(id);
        return null;
    }

    public Map<String, Object> login(String email, String password){

        if(email == null || email.isEmpty() || password == null || password.isEmpty()){
            return null;
        }

        PersonaDTO persona = personaRepository.findByEmail(email);
        Map<String, Object> res = null;
        if(persona != null){
            String passwordRequest = decryptPassword(password);
            String passwordBD = decryptPassword(persona.getPassword());
            if (passwordRequest.equals(passwordBD)) {
                res = new HashMap<>();
                res.put("TOKEN", getJWTToken(persona.getId(), persona.getNombre(), persona.getPerfil()));
                res.put("TOKEN_ID", persona.getId());
                res.put("NOMBRE", persona.getNombre());
                res.put("EMAIL",persona.getEmail());

                List<String> perfiles = persona.getPerfil();
                if(perfiles.contains("ROLE_ADMIN")){
                    res.put("ADMIN", 1);
                } else {
                    res.put("ADMIN", 0);
                }
            }
        }
        return res;
    }

    public PersonaDTO updatePersona(PersonaDTO persona){
        if(!persona.getId().isEmpty()){
            PersonaDTO actual = personaRepository.findById(persona.getId());

            if(persona.getNombre() != null){
                actual.setNombre(persona.getNombre());
            }

            if(persona.getEmail() != null){
                actual.setEmail(persona.getEmail());
            }

            if(persona.getPerfil() != null){
                actual.setPerfil(persona.getPerfil());
            }

            if(persona.getPassword() != null){
                actual.setPassword(persona.getPassword());
            }

            if(persona.getDireccion() != null){
                actual.setDireccion(persona.getDireccion());
            }

            if(persona.getProfesion() != null){
                actual.setProfesion(persona.getProfesion());
            }

            if(persona.getFechaNacimiento() != null){
                actual.setFechaNacimiento(persona.getFechaNacimiento());
            }

            if(persona.getSexo() != null){
                actual.setSexo(persona.getSexo());
            }

            if(persona.getPais() != null){
                actual.setPais(persona.getPais());
            }

            if(persona.getActivo() != null){
                actual.setActivo(persona.getActivo());
            }

            if(persona.getFoto() != null){
                actual.setFoto(persona.getFoto());
            }

            if(persona.getProyectos() != null){
                actual.setProyectos(persona.getProyectos());
            }

            return personaRepository.save(actual);
        }
        return null;
    }

    public void deletePersona(String id){
        if(id != null && !id.isEmpty())
            personaRepository.deleteById(id);
    }

    public List<PersonaDTO> consultAllPersonas(){
        return personaRepository.findAll();
    }

}
