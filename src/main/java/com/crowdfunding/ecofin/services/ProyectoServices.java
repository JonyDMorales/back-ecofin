package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.dtos.PersonaDTO;
import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.repositories.IPersonaRepository;
import com.crowdfunding.ecofin.repositories.IProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProyectoServices {

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private IPersonaRepository personaRepository;

    public ProyectoDTO insertPropyecto(ProyectoDTO proyectoDTO){

        Map<String,String> propietario =  proyectoDTO.getPropietario();

        String id = propietario.get("id");

        PersonaDTO propietarioDTO = personaRepository.findById(id);

        if(propietarioDTO != null){
            return proyectoRepository.insert(proyectoDTO);
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
            actual.setNombre(nuevo.getNombre());
            actual.setPropietario(nuevo.getPropietario());
            actual.setDescripcion(nuevo.getDescripcion());
            actual.setCategoria(nuevo.getCategoria());
            actual.setValorProyecto(nuevo.getValorProyecto());
            actual.setMontoAcumulado(nuevo.getMontoAcumulado());
            actual.setPorcentaje(nuevo.getPorcentaje());
            actual.setFinishDate(nuevo.getFinishDate());
            actual.setDiasFaltantes(nuevo.getDiasFaltantes());
            actual.setPais(nuevo.getPais());
            actual.setVisitas(nuevo.getVisitas());
            actual.setContribuyentes(nuevo.getContribuyentes());
        }
        return proyectoRepository.save(actual);
    }

    public void deleteProyecto(String id){
        proyectoRepository.deleteById(id);
    }

    public List<ProyectoDTO> getProyects(String id){
        return proyectoRepository.findByPropietario(id);
    }

}
