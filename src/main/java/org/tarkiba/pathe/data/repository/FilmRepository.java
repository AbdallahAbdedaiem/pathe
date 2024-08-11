package org.tarkiba.pathe.data.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tarkiba.pathe.data.model.Film;
import org.tarkiba.pathe.data.model.Film$;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository implements IFilmRepository  {
    @Inject
    JPAStreamer jpaStreamer;
    private static final short PAGE_LENGTH = 5;

    @Override
    public Optional<Film> getFilm(short filmID) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmID))
                .findFirst();
    }

    @Override
    public Stream<Film> paged(long page, short minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterOrEqual(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_LENGTH)
                .limit(PAGE_LENGTH);
    }
}
