package com.technicaltest.TechnicalTest.exception;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomResponseEntityExceptionHandlerTest {

    @InjectMocks
    private CustomResponseEntityExceptionHandler exceptionHandler;

    @Test
    void handleNoSuchElementException_ReturnsNotFoundResponse() {
        MockitoAnnotations.openMocks(this);
        NoSuchElementException exception = new NoSuchElementException();
        ResponseEntity<Object> responseEntity = exceptionHandler.handleNoSuchElementException(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Equipo no encontrado", responseEntity.getBody());
    }
}
