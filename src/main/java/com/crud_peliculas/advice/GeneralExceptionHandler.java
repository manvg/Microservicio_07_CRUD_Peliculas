package com.crud_peliculas.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crud_peliculas.model.dto.ResponseModel;

import java.util.HashMap;
import java.util.Map;
//Capturar errores
@RestControllerAdvice
public class GeneralExceptionHandler {
    //Metodo que retorna errores
    @ExceptionHandler(MethodArgumentNotValidException.class)//Cuando se produzca esta excepcion se dispara el método
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseModel> handleInternalServerError(Exception ex) {
        String errorMessage = "Error interno del servidor: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel(false, errorMessage));
    }
}
