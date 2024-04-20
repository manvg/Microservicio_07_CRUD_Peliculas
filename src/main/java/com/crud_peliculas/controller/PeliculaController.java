package com.crud_peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.crud_peliculas.model.dto.ResponseModel;
import com.crud_peliculas.model.entities.Pelicula;
import com.crud_peliculas.service.PeliculaService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/peliculas")
public class PeliculaController {


    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    // @GetMapping
    // public List<Pelicula> getAllPeliculas(){
    //     log.info("GET /students");
    //     log.info("Retornando todos los estudiantes");
    //     return peliculaService.getAllPeliculas();
    // }
    
    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        log.info("GET /pelicula");
        log.info("Retornando todas las peliculas");
        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
            .map( pelicula -> EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getIdPelicula())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("pelicula"));

        return resources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPeliculaById(@PathVariable Long id){
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
    // @GetMapping("/{id}")
    // public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
    //     var pelicula = peliculaService.getPeliculaById(id);

    //     if (pelicula != null) {
    //         return EntityModel.of(pelicula,
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    //     } else {
    //         throw new PeliculaNotFoundException("Pelicula no encontrada, id: " + id);
    //     }
    // }

    //---------MÉTODOS POST---------//
    //Crear película
    @PostMapping
    public ResponseEntity<Object> createPelicula(@RequestBody @Valid Pelicula pelicula){
        log.info("POST /peliculas/createPelicula");
        log.info("Creando pelicula...");        
        var response = peliculaService.createPelicula(pelicula);

        log.info("Película creado con éxito. Id: " + response.getIdPelicula());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // @PostMapping
    // public EntityModel<Pelicula> createPelicula(@Validated @RequestBody Pelicula pelicula) {
    //     Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
    //         return EntityModel.of(createdPelicula,
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createdPelicula.getIdPelicula())).withSelfRel(),
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));

    // }

    //---------MÉTODOS PUT---------//
    //Actualizar pelicula
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePelicula(@PathVariable Long id, @RequestBody @Valid Pelicula peliculaDto){
        log.info("PUT /peliculas/"+id);
        log.info("Actualizando pelicula con id " + id);
        var response = peliculaService.updatePelicula(id, peliculaDto);
        if (response == null) {
            log.error("No existe una pelicula con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(false,"La pelicula ingresada no existe."));
        }
        log.info("Pelicula actualizada con éxito. Id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // @PutMapping("/{id}")
    // public EntityModel<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
    //     Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
    //     return EntityModel.of(updatedPelicula,
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
    //             WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-pelicula"));

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePelicula(@PathVariable Long id){
        log.info("DELETE /peliculas/"+id);
        log.info("Eliminando pelicula con id " + id);

        var response = peliculaService.deletePelicula(id);
        if (!response.getStatus()) {
            log.info("No se eliminó pelicula con id " + id + ". " + response.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        log.info("Pelicula eliminado con éxito");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // @DeleteMapping("/{id}")
    // public void deletePelicula(@PathVariable Long id){
    //     peliculaService.deletePelicula(id);
    // }


    // static class ErrorResponse {
    //     private final String message;
    
    //     public ErrorResponse(String message) {
    //         this.message = message;
    //     }
    
    //     public String getMessage() {
    //         return message;
    //     }
    // }


}
