package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import com.crowdfunding.ecofin.repositories.IProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProyectoServices {

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private PersonaServices personaServices;

    public ProyectoDTO insertPropyecto(ProyectoDTO proyectoDTO){

        Map<String,String> propietario =  proyectoDTO.getPropietario();

        String id = propietario.get("id");

        PersonaDTO propietarioDTO = personaServices.findById(id);

        if(propietarioDTO != null){
            ProyectoDTO proyectoDTNuevo = proyectoRepository.insert(proyectoDTO);
            List<String> proyectos = propietarioDTO.getProyectos();
            if(proyectos == null){
                proyectos = new ArrayList<>(1);
            }
            proyectos.add(proyectoDTNuevo.getId());
            propietarioDTO.setProyectos(proyectos);
            personaServices.updatePersona(propietarioDTO);
            return proyectoDTNuevo;
        }

        return null;
    }

    public List<ProyectoDTO> consultAllProyects(){
        return proyectoRepository.findAll();
    }

    public ProyectoDTO findById(String id){
        return proyectoRepository.findById(id);
    }

    public ProyectoDTO updateProyecto(ProyectoDTO nuevo){
        ProyectoDTO actual = proyectoRepository.findById(nuevo.getId());
        if(actual != null) {

            if(nuevo.getNombre() != null){
                actual.setNombre(nuevo.getNombre());
            }

            if(nuevo.getFoto() != null){
                actual.setFoto(nuevo.getFoto());
            }

            if(nuevo.getPropietario() != null){
                actual.setPropietario(nuevo.getPropietario());
            }

            if(nuevo.getDescripcion()!= null){
                actual.setDescripcion(nuevo.getDescripcion());
            }

            if(nuevo.getCategoria()!= null){
                actual.setCategoria(nuevo.getCategoria());
            }

            if(nuevo.getValorProyecto()!= null){
                actual.setValorProyecto(nuevo.getValorProyecto());
            }

            if(nuevo.getMontoAcumulado()!= null){
                actual.setMontoAcumulado(nuevo.getMontoAcumulado());
            }

            if(nuevo.getPorcentaje()!= null){
                actual.setPorcentaje(nuevo.getPorcentaje());
            }

            if(nuevo.getFinishDate()!= null){
                actual.setFinishDate(nuevo.getFinishDate());
            }

            if(nuevo.getDiasFaltantes()!= null){
                actual.setDiasFaltantes(nuevo.getDiasFaltantes());
            }

            if(nuevo.getPais()!= null){
                actual.setPais(nuevo.getPais());
            }

            if(nuevo.getVisitas()!= null){
                actual.setVisitas(nuevo.getVisitas());
            }

            if(nuevo.getPrioridad()!= null){
                actual.setPrioridad(nuevo.getPrioridad());
            }

            if(nuevo.getContribuyentes()!= null) {
                actual.setContribuyentes(nuevo.getContribuyentes());
            }

            if(nuevo.getActivo()!= null) {
                actual.setActivo(nuevo.getActivo());
            }
            return proyectoRepository.save(actual);
        }
        return null;
    }

    public void deleteProyecto(String id){
        proyectoRepository.deleteById(id);
    }

    public List<ProyectoDTO> getProyects(String id){
        return proyectoRepository.findByPropietario(id);
    }

}
