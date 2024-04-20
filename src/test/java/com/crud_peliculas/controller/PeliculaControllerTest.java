package com.crud_peliculas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.crud_peliculas.model.entities.Pelicula;
import com.crud_peliculas.service.PeliculaService;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    private Pelicula pelicula1;
    private Pelicula pelicula2;

    @BeforeEach
    public void InicializarPruebas(){
        pelicula1 = new Pelicula();
        pelicula1.setAnio(2023);
        pelicula1.setDirector("Christopher Nolan");
        pelicula1.setGenero("Drama");
        pelicula1.setSinopsis("En tiempos de guerra, el brillante físico estadounidense Julius Robert Oppenheimer, al frente del Proyecto Manhattan...");
        pelicula1.setTitulo("Oppenheimer");
        pelicula1.setId(1L);

        pelicula2 = new Pelicula();
        pelicula2.setAnio(2014);
        pelicula2.setDirector("Christopher Nolan");
        pelicula2.setGenero("Ciencia Ficción");
        pelicula2.setSinopsis("Al ver que la vida en la Tierra está llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper...");
        pelicula2.setTitulo("Interstellar");
        pelicula2.setId(2L);
    }

    @Test
    @DisplayName("Obtener todas las películas -> OK")
    public void getAllPeliculasTest() throws Exception{

        List<Pelicula> peliculas = List.of(pelicula1, pelicula2);
        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculaList.length()").value(2))
                .andExpect(jsonPath("$._embedded.peliculaList[0].anio").value(2023))
                .andExpect(jsonPath("$._embedded.peliculaList[0].director").value("Christopher Nolan"))
                .andExpect(jsonPath("$._embedded.peliculaList[0].genero").value("Drama"))
                .andExpect(jsonPath("$._embedded.peliculaList[0].sinopsis").value("En tiempos de guerra, el brillante físico estadounidense Julius Robert Oppenheimer, al frente del Proyecto Manhattan..."))
                .andExpect(jsonPath("$._embedded.peliculaList[0].titulo").value("Oppenheimer"))
                .andExpect(jsonPath("$._embedded.peliculaList[0]._links.self.href").value("http://localhost/peliculas/1"))
                .andExpect(jsonPath("$._embedded.peliculaList[1].anio").value(2014))
                .andExpect(jsonPath("$._embedded.peliculaList[1].director").value("Christopher Nolan"))
                .andExpect(jsonPath("$._embedded.peliculaList[1].genero").value("Ciencia Ficción"))
                .andExpect(jsonPath("$._embedded.peliculaList[1].sinopsis").value("Al ver que la vida en la Tierra está llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper..."))
                .andExpect(jsonPath("$._embedded.peliculaList[1].titulo").value("Interstellar"))
                .andExpect(jsonPath("$._embedded.peliculaList[1]._links.self.href").value("http://localhost/peliculas/2"));
    }

    @Test
    @DisplayName("Obtener película por id -> OK")
    public void getPeliculaByIdTest() {
        Long idPelicula = 1L;
        when(peliculaServiceMock.getPeliculaById(idPelicula)).thenReturn(pelicula1);

        Pelicula responseEntity = peliculaServiceMock.getPeliculaById(idPelicula);

        assertEquals(pelicula1, responseEntity);
    }

    @Test
    @DisplayName("Obtener película por ID no existe")
    public void getPeliculaByIdTest_NoExiste() {
        Long idPeliculaNoExistente = 5L;

        when(peliculaServiceMock.getPeliculaById(idPeliculaNoExistente)).thenReturn(null);

        Pelicula pelicula = peliculaServiceMock.getPeliculaById(idPeliculaNoExistente);

        assertNull(pelicula, "La película no debe existir para este ID");
    }
}
