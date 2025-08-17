package com.vouchers.controller;

import com.vouchers.dto.error.ResponseErrorDTO;
import com.vouchers.exception.DuplicatedRegistryException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRegistryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO handleDuplicatedRegistryException(DuplicatedRegistryException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.conflict(e.getMessage(), path);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDTO handleGenericException(Exception e, HttpServletRequest request) {
        String path = request.getRequestURI();

        String rootCauseMessage = "";
        if (e.getCause() != null) {
            rootCauseMessage = " - Root cause: " + e.getCause().getClass().getSimpleName()
                    + " - Root message: " + e.getCause().getMessage();
        }

        return ResponseErrorDTO.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                List.of(),
                path
        );
    }
}
