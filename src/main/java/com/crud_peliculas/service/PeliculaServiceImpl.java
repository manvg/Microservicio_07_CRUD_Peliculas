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
    public List<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula getPeliculaById(Long id){
        var pelicula = peliculaRepository.findById(id);
        if (!pelicula.isEmpty()) {
            return pelicula.get();
        }else{
            return null;
        }
    }

    //---------POST---------//
    @Override
    public Pelicula createPelicula(Pelicula objPelicula){
        //Pelicula pelicula = peliculaMapper.convertirAEntity(peliculaDto);//Mapeo
        return peliculaRepository.save(objPelicula);
        //return new ResponseModel(true, "Pelicula creado con éxito. Id: " + resultado.getIdPelicula());
    }
    
    //---------PUT---------//
    @Override
    public Pelicula updatePelicula(Long id, Pelicula objPelicula){
        var peliculaExiste = peliculaRepository.findById(id);
        if (!peliculaExiste.isEmpty()) {
            Pelicula pelicula = peliculaExiste.get();
            pelicula.setAnio(objPelicula.getAnio());
            pelicula.setDirector(objPelicula.getDirector());
            pelicula.setGenero(objPelicula.getGenero());
            pelicula.setSinopsis(objPelicula.getSinopsis());
            pelicula.setTitulo(objPelicula.getTitulo());

            return peliculaRepository.save(pelicula);
            //return new ResponseModel(true, "Película actualizada con éxito. Id: " + resultado.getIdPelicula());
        }else{
            return null;
            //return new ResponseModel(false, "La película ingresada no existe");
        }
    }

    //---------DELETE---------//
    @Override
    public ResponseModel deletePelicula(Long id){
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
            return new ResponseModel(true, "Película eliminada con éxito");
        }else{
            return new ResponseModel(false, "La película ingresada no existe");
        }
    }
}
