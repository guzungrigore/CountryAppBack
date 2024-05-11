package org.example.pw7.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.example.pw7.domain.Country;
import org.example.pw7.dto.CountryDto;
import org.example.pw7.dto.ResponseDto;
import org.example.pw7.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Page<Country> getCountries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return countryRepository.findAll(pageable);
    }

    public Country getCountry(long id) {
        return getCountryFromDB(id);
    }

    public ResponseDto deleteCountry(long id) {
        Country country = getCountryFromDB(id);
        String name = country.getName();
        countryRepository.deleteById(id);
        return new ResponseDto(name + " was deleted successfully");
    }

    public ResponseDto createCountry(CountryDto countryDto) {
        countryRepository.findOneByName(countryDto.name())
                .ifPresent(existing -> {
                    throw new EntityExistsException("Country with name " + countryDto.name() + " already exists.");
                });

        Country newCountry = new Country();
        upsertCountry(countryDto, newCountry);

        return new ResponseDto("Country created successfully");
    }


    public ResponseDto updateCountry(Long id, CountryDto countryDto) {
        Country country = getCountryFromDB(id);

        upsertCountry(countryDto, country);

        return new ResponseDto("Country updated successfully");
    }

    private Country getCountryFromDB(long id) {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void upsertCountry(CountryDto countryDto, Country country) {
        country.setName(countryDto.name());
        country.setCapital(countryDto.capital());
        country.setContinent(countryDto.continent());
        country.setPopulation(countryDto.population());
        country.setLanguage(countryDto.language());
        country.setImage(countryDto.image());

        countryRepository.save(country);
    }

}
