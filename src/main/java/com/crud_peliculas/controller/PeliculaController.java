package com.crud_peliculas.controller;
import com.crud_peliculas.model.dto.PeliculaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud_peliculas.service.PeliculaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<PeliculaDto> getAllPeliculas(){
        log.info("GET /peliculas -> getAllPeliculas");
        return peliculaService.getAllPeliculas();
    }
}
