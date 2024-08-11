package org.tarkiba.pathe.data.repository;

import org.tarkiba.pathe.data.model.Film;

import java.util.Optional;

public interface IFilmRepository {
    public Optional<Film> getFilm(short filmID);
}
