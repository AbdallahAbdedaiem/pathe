package org.tarkiba.pathe.data.repository;

import org.tarkiba.pathe.data.model.Film;

import java.util.Optional;
import java.util.stream.Stream;

public interface IFilmRepository {
    Optional<Film> getFilm(short filmID);
    Stream<Film> paged(long page, short minLength);
    Stream<Film> actors(String startsWith, short minLength);
    void updateRentalRate(short minLength, Float rentalRate);
    Stream<Film> films(short minLength);

}
