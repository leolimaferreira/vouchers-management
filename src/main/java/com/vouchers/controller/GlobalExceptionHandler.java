package com.vouchers.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vouchers.dto.error.FieldErrorDTO;
import com.vouchers.dto.error.ResponseErrorDTO;
import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.exception.InvalidFieldException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRegistryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO handleDuplicatedRegistryException(DuplicatedRegistryException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.conflict(e.getMessage(), path);
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleInvalidFieldException(InvalidFieldException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.of(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                List.of(new FieldErrorDTO(e.getField(), e.getMessage())),
                path
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String path = request.getRequestURI();

        List<FieldErrorDTO> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseErrorDTO.of(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                fieldErrors,
                path
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String path = request.getRequestURI();

        List<FieldErrorDTO> fieldErrors = e.getConstraintViolations()
                .stream()
                .map(violation -> new FieldErrorDTO(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
                ))
                .collect(Collectors.toList());

        return ResponseErrorDTO.of(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                fieldErrors,
                path
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        String path = request.getRequestURI();

        String message = "Invalid data format";
        String fieldName = "unknown";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormat = (InvalidFormatException) e.getCause();
            fieldName = invalidFormat.getPath().isEmpty() ? "unknown" :
                       invalidFormat.getPath().get(invalidFormat.getPath().size() - 1).getFieldName();

            if (invalidFormat.getTargetType().isEnum()) {
                Object[] enumValues = invalidFormat.getTargetType().getEnumConstants();
                String validValues = java.util.Arrays.stream(enumValues)
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
                message = String.format("Invalid value for %s. Accepted values: %s", fieldName, validValues);
            }
        }

        return ResponseErrorDTO.of(
                HttpStatus.BAD_REQUEST,
                "Deserialization error",
                List.of(new FieldErrorDTO(fieldName, message)),
                path
        );
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
