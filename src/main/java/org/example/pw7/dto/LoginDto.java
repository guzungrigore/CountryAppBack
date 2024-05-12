package org.example.pw7.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDto(@NotNull @Size(min = 1, max = 50) @Schema(example = "user") String username,
                       @NotNull @Size(min = 4, max = 100) @Schema(example = "user") String password) {
}
