package org.example.pw7.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.example.pw7.domain.Country;
import org.example.pw7.dto.CountryDto;
import org.example.pw7.dto.ResponseDto;
import org.example.pw7.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Country", description = "The Country API. Contains all the operations that can be performed on a country.")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get a list of countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned a list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "401", description = "Not logged in",
                    content = @Content)})
    @GetMapping("/country")
    public ResponseEntity<Page<Country>> getCountries(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size) {
        return ResponseEntity.status(OK).body(countryService.getCountries(page, size));
    }

    @Operation(summary = "Get a country by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the country",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content) })
    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable @NotNull long id) {
        return ResponseEntity.status(OK).body(countryService.getCountry(id));
    }

    @Operation(summary = "Delete a country by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the country",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Permission denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content) })
    @DeleteMapping("country/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable @NotNull long id) {
        return ResponseEntity.status(OK).body(countryService.deleteCountry(id));
    }

    @Operation(summary = "Add a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country added successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "401", description = "Not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Permission denied",
                    content = @Content)})
    @PostMapping("/country")
    public ResponseEntity<ResponseDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.status(CREATED).body(countryService.createCountry(countryDto));
    }

    @Operation(summary = "Edit a country by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country edited successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Permission denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content) })
    @PutMapping("/country/{id}")
    public ResponseEntity<ResponseDto> updateCountry(@PathVariable @NotNull long id, @RequestBody CountryDto countryDto) {
        return ResponseEntity.status(OK).body(countryService.updateCountry(id, countryDto));
    }
}
