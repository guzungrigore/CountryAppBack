package org.example.pw7.repository;

import org.example.pw7.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

   // Page<Country> findAll(Pageable pageable);

    //Optional<Country> findById(@NotNull Long id);

    Optional<Country> findOneByName(String name);
}
