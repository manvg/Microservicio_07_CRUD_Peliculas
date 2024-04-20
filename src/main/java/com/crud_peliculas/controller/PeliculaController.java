package com.crud_peliculas.controller;
import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.dto.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud_peliculas.service.PeliculaService;

import jakarta.validation.Valid;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(false,"La película ingresada no existe."));
        }
        log.info("Pelicula encontrada con éxito. Id: " + id);
        return ResponseEntity.ok(response);
    }

    //---------MÉTODOS POST---------//
    //Crear película
    @PostMapping
    public ResponseEntity<Object> createPelicula(@RequestBody @Valid PeliculaDto pelicula){
        log.info("POST /peliculas/createPelicula");
        log.info("Creando pelicula...");

        var response = peliculaService.createPelicula(pelicula);

        log.info(response.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //---------MÉTODOS PUT---------//
    //Actualizar pelicula
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePelicula(@PathVariable Integer id, @RequestBody @Valid PeliculaDto peliculaDto){
        log.info("PUT /peliculas/"+id);
        log.info("Actualizando pelicula con id " + id);
        var response = peliculaService.updatePelicula(id, peliculaDto);
        if (response == null) {
            log.error("No existe una pelicula con id " + id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(false,"La pelicula ingresada no existe."));
        }
        log.info("Pelicula actualizada con éxito. Id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //---------MÉTODOS DELETE---------//
    //Eliminar pelicula
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePelicula(@PathVariable Integer id){
        log.info("DELETE /peliculas/"+id);
        log.info("Eliminando pelicula con id " + id);
  
        var response = peliculaService.deletePelicula(id);
        if (!response.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        log.info("Pelicula eliminado con éxito");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
