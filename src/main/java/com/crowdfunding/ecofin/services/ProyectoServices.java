package com.crowdfunding.ecofin.services;

import com.crowdfunding.ecofin.dtos.ProyectoDTO;
import com.crowdfunding.ecofin.repositories.IProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServices {

    @Autowired
    private IProyectoRepository proyectoRepository;

    public ProyectoDTO insertPropyecto(ProyectoDTO proyectoDTO){
        return proyectoRepository.insert(proyectoDTO);
    }
}
