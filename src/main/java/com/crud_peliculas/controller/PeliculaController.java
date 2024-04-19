package com.crud_peliculas.controller;
import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.dto.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //---------MÉTODOS GET---------//

    //Obtener lista completa de peliculas
    @GetMapping
    public List<PeliculaDto> getAllPeliculas(){
        log.info("GET /peliculas -> getAllPeliculas");
        return peliculaService.getAllPeliculas();
    }

    //Obtener pelicula por su id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeliculaById(@PathVariable Integer id){
        log.info("GET /peliculas/" + id + " -> getPeliculaById");
        log.info("Obteniendo pelicula por id " + id);
        var response = peliculaService.getPeliculaById(id);
        if (response == null) {
            log.error("No se encontro pelicula con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(false,"El pelicula ingresada no existe."));
        }
        log.info("Pelicula encontrada con éxito. Id: " + id);
        return ResponseEntity.ok(response);
    }
}
