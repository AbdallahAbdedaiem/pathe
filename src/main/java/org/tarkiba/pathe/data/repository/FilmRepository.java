package org.tarkiba.pathe.data.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
        // INFO need also a matching constructor on the entity for this projection
        return jpaStreamer.stream(Projection.select(Film$.filmId, Film$.title, Film$.length))
                .filter(Film$.length.greaterOrEqual(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_LENGTH)
                .limit(PAGE_LENGTH);
    }

    @Override
    public Stream<Film> actors(String startsWith, short minLength) {
        final StreamConfiguration<Film> sc = StreamConfiguration.of(Film.class).joining(Film$.actors);
        return jpaStreamer.stream(sc)
                .filter(Film$.title.startsWith(startsWith).and(Film$.length.greaterOrEqual(minLength)))
                .sorted(Film$.length.reversed());
    }

    @Override
    // transactional here means we persist any updates we make on the entity
    @Transactional
    public void updateRentalRate(short minLength, Float rentalRate) {
        jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterOrEqual(minLength))
                .forEach(f -> f.setRentalRate(rentalRate));
    }

    @Override
    public Stream<Film> films(short minLength) {
        return jpaStreamer.stream(Projection.select(Film$.filmId, Film$.title, Film$.length, Film$.rentalRate))
                .filter(Film$.length.greaterOrEqual(minLength))
                .sorted(Film$.length);
    }
}

























