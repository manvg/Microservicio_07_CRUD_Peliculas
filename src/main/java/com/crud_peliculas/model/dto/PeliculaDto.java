package com.crud_peliculas.model.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDto {
    private Long idpelicula;

    @Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
    private String titulo;

    @Min(value = 1901, message = "El año debe ser mayor a 1900")
    private int anio;

    @Size(min = 2, max = 150, message = "Debe tener entre 2 y 100 caracteres")
    private String director;

    @Size(min = 2, max = 50, message = "Debe tener entre 2 y 100 caracteres")
    private String genero;

    @Size(min = 2, max = 700, message = "Debe tener entre 2 y 100 caracteres")
    private String sinopsis;
}
