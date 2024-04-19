package com.crud_peliculas.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.entities.Pelicula;


@Component
public class PeliculaMapper {
    private final ModelMapper modelMapper;

    public PeliculaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public PeliculaDto convertirADTO(Pelicula pelicula) {
        return modelMapper.map(pelicula, PeliculaDto.class);
    }

    public Pelicula convertirAEntity(PeliculaDto peliculaDto){
        return modelMapper.map(peliculaDto, Pelicula.class);
    }
}