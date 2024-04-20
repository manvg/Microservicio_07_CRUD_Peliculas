package com.crud_peliculas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud_peliculas.model.dto.PeliculaDto;
import com.crud_peliculas.model.entities.Pelicula;
import com.crud_peliculas.repository.PeliculaRepository;
import com.crud_peliculas.utilities.PeliculaMapper;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServiceImpl peliculaServicio;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Test
    public void guardarPeliculaTest() {

        Pelicula pelicula = new Pelicula();
        pelicula.setAnio(2024);
        pelicula.setDirector("Service Director test");
        pelicula.setGenero("Service Acción test");
        pelicula.setSinopsis("Service Sinopsis test");
        pelicula.setTitulo("Service Titulo test");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaServicio.createPelicula(pelicula);
        assertNotNull(resultado.getIdPelicula());
        assertEquals(2024, resultado.getAnio());
        assertEquals("Service Director test", resultado.getDirector());
        assertEquals("Service Acción test", resultado.getGenero());
        assertEquals("Service Sinopsis test", resultado.getSinopsis());
        assertEquals("Service Titulo test", resultado.getTitulo());
    }
}
