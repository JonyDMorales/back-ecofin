package com.crowdfunding.ecofin.services;


import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonaServices {

    @Autowired
    private IPersonaRepository personaRepository;

    @Value("${jwt.secret}")
    private  String secretKey;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    private String getJWTToken(String id, String username, List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String rol :roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(rol));
        }
        //List grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles.toString());
        System.out.println(grantedAuthorities);
        String token = Jwts.builder().setId(id).setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return token;
    }

    public PersonaDTO savePersona(PersonaDTO persona){ return  personaRepository.insert(persona); }

    public PersonaDTO findById(String id){
        return personaRepository.findById(id);
    }

    public Map<String, Object>  login(String email, String password){
        PersonaDTO persona = personaRepository.findByEmailAndPassword(email, password);
        Map<String, Object> res = new HashMap<>();
        if(persona != null){
            res.put("TOKEN", getJWTToken(persona.getId(), persona.getNombre(), persona.getPerfil()));
            res.put("NOMBRE", persona.getNombre());
            res.put("EMAIL",persona.getEmail());
        }
        return res;
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
