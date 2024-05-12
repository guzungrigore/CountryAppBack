package org.example.pw7.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponseDto(
        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiaHR0cHM6Ly93d3cueW91dHViZS5jb20vd2F0Y2g_dj1kUXc0dzlXZ1hjUSJ9.1YswKJWtQQ42swtTX4plZfJMZ3D1beJimWFHGPMoSYM")
        String accessToken
) {
}
