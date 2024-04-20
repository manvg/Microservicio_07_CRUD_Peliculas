package com.crud_peliculas.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import com.crud_peliculas.model.entities.Pelicula;
import com.crud_peliculas.service.PeliculaService;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAnio(2024);
        pelicula1.setDirector("Director test");
        pelicula1.setGenero("Acción test");
        pelicula1.setSinopsis("Sinopsis test");
        pelicula1.setTitulo("Titulo test");
        pelicula1.setId(Long.parseLong("1"));

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAnio(2023);
        pelicula2.setDirector("Director 2 test");
        pelicula2.setGenero("Acción 2 test");
        pelicula2.setSinopsis("Sinopsis 2 test");
        pelicula2.setTitulo("Titulo 2 test");
        pelicula2.setId(Long.parseLong("2"));

        List<Pelicula> lstPeliculas = List.of(pelicula1, pelicula2);
        when(peliculaServiceMock.getAllPeliculas()).thenReturn(lstPeliculas);

        // List<EntityModel<Pelicula>> studentsResources = lstPeliculas.stream()
        //     .map(pelicula -> EntityModel.of(pelicula))
        //     .collect(Collectors.toList());

        mockMvc.perform(get("/peliculas")).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.students.length()").value(2))
                .andExpect(jsonPath("$._embedded.students[0].anio").value(2024))
                .andExpect(jsonPath("$._embedded.students[0].director").value("Director test"))
                .andExpect(jsonPath("$._embedded.students[0].genero").value("Genero test"))
                .andExpect(jsonPath("$._embedded.students[0].sinopsis").value("Sinopsis test"))
                .andExpect(jsonPath("$._embedded.students[0].titulo").value("Titulo test"))
                .andExpect(jsonPath("$._embedded.students[0]._links.self.href").value("http://localhost:8087/peliculas/1"))
                .andExpect(jsonPath("$._embedded.students[1].anio").value(2023))
                .andExpect(jsonPath("$._embedded.students[1].director").value("Director 2 test"))
                .andExpect(jsonPath("$._embedded.students[1].genero").value("Genero 2 test"))
                .andExpect(jsonPath("$._embedded.students[1].sinopsis").value("Sinopsis 2 test"))
                .andExpect(jsonPath("$._embedded.students[1].titulo").value("Titulo 2 test"))
                .andExpect(jsonPath("$._embedded.students[1]._links.self.href").value("http://localhost:8087/peliculas/2"));
    }
}
