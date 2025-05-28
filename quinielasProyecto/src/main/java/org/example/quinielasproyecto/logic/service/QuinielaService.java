package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.QuinielaRepository;
import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuinielaService {

    @Autowired
    private QuinielaRepository quinielaRepository;

    public void registrarQuiniela(QuinielaRequest request) {
        quinielaRepository.registrarQuiniela(request);
    }
}