package com.crud_peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.crud_peliculas.model.entities.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest(){
        Pelicula pelicula = new Pelicula();
        pelicula.setAnio(2024);
        pelicula.setDirector("Director test");
        pelicula.setGenero("Acción test");
        pelicula.setSinopsis("Sinopsis test");
        pelicula.setTitulo("Titulo test");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getIdPelicula());
        assertEquals(2024, resultado.getAnio());
        assertEquals("Director test", resultado.getDirector());
        assertEquals("Acción test", resultado.getGenero());
        assertEquals("Sinopsis test", resultado.getSinopsis());
        assertEquals("Titulo test", resultado.getTitulo());
    }
}
