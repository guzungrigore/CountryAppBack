package org.example.pw7.dto;

public record ValidationExceptionResponse(
        String fieldName,
        String errorMessage
) {
}
