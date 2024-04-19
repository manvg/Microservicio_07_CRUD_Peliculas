package com.crud_peliculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud_peliculas.repository.PeliculaRepository;
import com.crud_peliculas.utilities.PeliculaMapper;
import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.entities.Pelicula;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Override
    public List<PeliculaDto> getAllPeliculas(){
        List<Pelicula> lstPeliculas = peliculaRepository.findAll();
        return lstPeliculas.stream().map(peliculaMapper::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public PeliculaDto getPeliculaById(Integer id){
        var pelicula = peliculaRepository.findById(id);
        if (!pelicula.isEmpty()) {
            return peliculaMapper.convertirADTO(pelicula.get());
        }else{
            return null;
        }
    }
    
}
