package org.tarkiba.pathe.data.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tarkiba.pathe.data.model.Film;
import org.tarkiba.pathe.data.model.Film$;

import java.util.Optional;

@ApplicationScoped
public class FilmRepository implements IFilmRepository  {
    @Inject
    JPAStreamer jpaStreamer;

    @Override
    public Optional<Film> getFilm(short filmID) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmID))
                .findFirst();
    }
}
