package org.example.pw7.dto;

import jakarta.validation.constraints.NotBlank;

public record CountryDto(
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String capital,

        @NotBlank
        String continent,

        @NotBlank
        String population,

        @NotBlank
        String language,

        @NotBlank
        String image
) {
}
