package com.crud_peliculas.service;

import java.util.List;

import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.dto.ResponseModel;

public interface PeliculaService {
    List<PeliculaDto> getAllPeliculas();
    PeliculaDto getPeliculaById(Integer id);
    ResponseModel createPelicula(PeliculaDto usuario);
    ResponseModel updatePelicula(Integer id, PeliculaDto usuario);
    ResponseModel deletePelicula(Integer id);
}
