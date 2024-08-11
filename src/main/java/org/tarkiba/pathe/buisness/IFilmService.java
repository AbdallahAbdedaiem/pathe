package org.tarkiba.pathe.buisness;

import org.tarkiba.pathe.data.model.Film;

import java.util.Optional;
import java.util.stream.Stream;

public interface IFilmService {
    String getFilmTitle(short filmID);

    String pagedFilms(long page, short minLength);

    String actors(String statsWith, short minLength);
    String updateRentalRate(short minLength, Float rentalRate);

}
