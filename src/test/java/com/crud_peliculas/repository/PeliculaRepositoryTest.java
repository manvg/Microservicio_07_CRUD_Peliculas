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
        pelicula.setDirector("Director TEST");
        pelicula.setGenero("Acción TEST");
        pelicula.setSinopsis("Sinopsis TEST");
        pelicula.setTitulo("Titulo TEST");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getIdPelicula());
        assertEquals(2024, resultado.getAnio());
        assertEquals("Director TEST", resultado.getDirector());
        assertEquals("Acción TEST", resultado.getGenero());
        assertEquals("Sinopsis TEST", resultado.getSinopsis());
        assertEquals("Titulo TEST", resultado.getTitulo());
    }
}
