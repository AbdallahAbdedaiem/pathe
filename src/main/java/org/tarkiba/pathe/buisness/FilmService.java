package org.tarkiba.pathe.buisness;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tarkiba.pathe.data.model.Film;
import org.tarkiba.pathe.data.repository.IFilmRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmService implements IFilmService {
    @Inject
    IFilmRepository filmRepository;

    @Override
    public String getFilmTitle(short filmID) {
        Optional<Film> filmOptional = filmRepository.getFilm(filmID);
        return filmOptional.isPresent() ? filmOptional.get().getTitle() : "No film was found";
    }

    @Override
    public String pagedFilms(long page, short minLength) {
        return filmRepository.paged(page, minLength)
                .map(f ->  String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String actors(String startsWith, short minLength) {
        return filmRepository.actors(startsWith, minLength)
                .map(f ->  String.format("%s (%d min) %s",
                                f.getTitle(),
                                f.getLength(),
                                f.getActors().stream()
                                        .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                                        .collect(Collectors.joining(", "))
                        )
                )
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String updateRentalRate(short minLength, Float rentalRate) {
        filmRepository.updateRentalRate(minLength, rentalRate);
        return filmRepository.films(minLength)
                .map(f ->  String.format("%s (%d min) - $ %f", f.getTitle(), f.getLength(), f.getRentalRate()))
                .collect(Collectors.joining("\n"));
    }
}
