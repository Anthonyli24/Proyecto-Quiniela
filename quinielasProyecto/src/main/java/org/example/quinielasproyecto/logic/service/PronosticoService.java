package org.example.quinielasproyecto.logic.service;


import org.example.quinielasproyecto.data.PronosticoRepository;
import org.example.quinielasproyecto.logic.Entidades.Pronostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PronosticoService {

    @Autowired
    private PronosticoRepository pronosticoRepository;


    public void registrarPronostico(Pronostico pronostico) {
        pronosticoRepository.save(pronostico);
    }
}
