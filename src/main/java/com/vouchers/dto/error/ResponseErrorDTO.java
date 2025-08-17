package com.vouchers.dto.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;


public record ResponseErrorDTO(
        int status,
        String message,
        List<FieldErrorDTO> errors,
        LocalDateTime timestamp,
        String path
) {

    public static ResponseErrorDTO defaultResponse(String message, String path) {
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), message, List.of(), LocalDateTime.now(), path);
    }

    public static ResponseErrorDTO conflict(String message, String path) {
        return new ResponseErrorDTO(HttpStatus.CONFLICT.value(), message, List.of(), LocalDateTime.now(), path);
    }

    public static ResponseErrorDTO unprocessableEntity(String message, List<FieldErrorDTO> errors, String path) {
        return new ResponseErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), message, errors, LocalDateTime.now(), path);
    }

    public static ResponseErrorDTO of(HttpStatus status, String message, List<FieldErrorDTO> errors, String path) {
        return new ResponseErrorDTO(status.value(), message, errors, LocalDateTime.now(), path);
    }
}
