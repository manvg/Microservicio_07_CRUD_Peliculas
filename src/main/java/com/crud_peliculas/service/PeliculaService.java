package com.crud_peliculas.service;

import java.util.List;
import java.util.Optional;

import com.crud_peliculas.model.dto.PeliculaDto;

public interface PeliculaService {
    List<PeliculaDto> getAllPeliculas();
    PeliculaDto getPeliculaById(Integer id);
}
