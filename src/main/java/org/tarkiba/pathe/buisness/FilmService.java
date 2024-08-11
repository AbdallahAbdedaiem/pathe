package org.tarkiba.pathe.buisness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tarkiba.pathe.data.model.Film;
import org.tarkiba.pathe.data.repository.IFilmRepository;

import java.util.Optional;

@ApplicationScoped
public class FilmService implements IFilmService {
    @Inject
    IFilmRepository filmRepository;

    @Override
    public String getFilmTitle(short filmID) {
        Optional<Film> filmOptional = filmRepository.getFilm(filmID);
        return filmOptional.isPresent() ? filmOptional.get().getTitle() : "No film was found";
    }
}
