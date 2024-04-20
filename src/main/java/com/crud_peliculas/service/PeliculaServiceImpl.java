package com.crud_peliculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud_peliculas.repository.PeliculaRepository;
import com.crud_peliculas.utilities.PeliculaMapper;
import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.dto.ResponseModel;
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

    //---------POST---------//
    @Override
    public ResponseModel createPelicula(PeliculaDto peliculaDto){
        Pelicula pelicula = peliculaMapper.convertirAEntity(peliculaDto);//Mapeo
        var resultado = peliculaRepository.save(pelicula);
        return new ResponseModel(true, "Pelicula creado con éxito. Id: " + resultado.getIdPelicula());
    }
    
    //---------PUT---------//
    @Override
    public ResponseModel updatePelicula(Integer id, PeliculaDto peliculaDto){
        var peliculaExiste = peliculaRepository.findById(id);
        if (!peliculaExiste.isEmpty()) {
            Pelicula pelicula = peliculaExiste.get();
            pelicula.setAnio(peliculaDto.getAnio());
            pelicula.setDirector(peliculaDto.getDirector());
            pelicula.setGenero(peliculaDto.getGenero());
            pelicula.setSinopsis(pelicula.getSinopsis());
            pelicula.setTitulo(peliculaDto.getTitulo());

            var resultado = peliculaRepository.save(pelicula);
            return new ResponseModel(true, "Película actualizada con éxito. Id: " + resultado.getIdPelicula());
        }else{
            return new ResponseModel(false, "La película ingresada no existe");
        }
    }

    //---------DELETE---------//
    @Override
    public ResponseModel deletePelicula(Integer id){
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
            return new ResponseModel(true, "Película eliminada con éxito");
        }else{
            return new ResponseModel(false, "La película ingresada no existe");
        }
    }
}
