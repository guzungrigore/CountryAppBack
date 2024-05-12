package org.example.pw7.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.temporal.ChronoUnit;

@ConfigurationProperties(prefix = "country")
public record AppProperties(Jwt jwt) {
    public record Jwt(@NotEmpty long duration,
                      @NotEmpty ChronoUnit chrono) {
    }

}
