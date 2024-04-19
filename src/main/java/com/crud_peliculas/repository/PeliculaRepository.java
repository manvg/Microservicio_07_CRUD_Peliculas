package com.crud_peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud_peliculas.model.entities.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

}
