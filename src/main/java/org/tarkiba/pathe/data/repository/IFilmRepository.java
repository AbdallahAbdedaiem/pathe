package org.tarkiba.pathe.data.repository;

import org.tarkiba.pathe.data.model.Film;

import java.util.Optional;
import java.util.stream.Stream;

public interface IFilmRepository {
    public Optional<Film> getFilm(short filmID);
    public Stream<Film> paged(long page, short minLength);
    public Stream<Film> actors(String startsWith, short minLength);
}
