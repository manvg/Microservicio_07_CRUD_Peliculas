package com.crud_peliculas.service;

import java.util.List;

import com.crud_peliculas.model.dto.PeliculaDto;

public interface PeliculaService {
    List<PeliculaDto> getAllPeliculas();
}
