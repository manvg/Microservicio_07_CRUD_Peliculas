package com.crud_peliculas.service;

import java.util.List;

import com.crud_peliculas.model.dto.ResponseModel;
import com.crud_peliculas.model.entities.Pelicula;

public interface PeliculaService {
    List<Pelicula> getAllPeliculas();
    Pelicula getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula usuario);
    Pelicula updatePelicula(Long id, Pelicula usuario);
    ResponseModel deletePelicula(Long id);
}
