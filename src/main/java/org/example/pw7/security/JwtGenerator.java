package org.example.pw7.security;

import org.example.pw7.config.AppProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

import static org.example.pw7.security.SecurityUtils.AUTHORITIES_KEY;
import static org.example.pw7.security.SecurityUtils.JWT_ALGORITHM;

@Component
public class JwtGenerator {

    private final AppProperties appProperties;
    private final JwtEncoder jwtEncoder;

    public JwtGenerator(AppProperties appProperties, JwtEncoder jwtEncoder) {
        this.appProperties = appProperties;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity = now.plus(appProperties.jwt().duration(), appProperties.jwt().chrono());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
