package org.example.pw7.controller;

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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public ResponseEntity<Page<Country>> getCountries(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.status(OK).body(countryService.getCountries(page, size));
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable @NotNull long id) {
        return ResponseEntity.status(OK).body(countryService.getCountry(id));
    }

    @DeleteMapping("country/{id}")
    public ResponseEntity<ResponseDto> deleteCountry(@PathVariable @NotNull long id) {
        return ResponseEntity.status(OK).body(countryService.deleteCountry(id));
    }

    @PostMapping("/country")
    public ResponseEntity<ResponseDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.status(CREATED).body(countryService.createCountry(countryDto));
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<ResponseDto> updateCountry(@PathVariable @NotNull long id, @RequestBody CountryDto countryDto) {
        return ResponseEntity.status(OK).body(countryService.updateCountry(id, countryDto));
    }
}
