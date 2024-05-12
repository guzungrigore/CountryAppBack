package org.example.pw7.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CountryDto(
        @JsonIgnore
        Long id,

        @NotBlank
        @Schema(example = "Mexico")
        String name,

        @NotBlank
        @Schema(example = "Mexico City")
        String capital,

        @NotBlank
        @Schema(example = "Americas")
        String continent,

        @NotBlank
        @Schema(example = "128932753")
        String population,

        @NotBlank
        @Schema(example = "Spanish")
        String language,

        @NotBlank
        @Schema(example = "https://flagcdn.com/w320/mx.png")
        String image
) {
}
