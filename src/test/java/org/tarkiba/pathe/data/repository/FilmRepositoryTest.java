package org.tarkiba.pathe.data.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tarkiba.pathe.data.model.Film;

import java.util.Optional;

@QuarkusTest
public class FilmRepositoryTest {
    @Inject
    IFilmRepository filmRepository;


    @Test
    public void test() {
        Optional<Film> film = filmRepository.getFilm((short) 5);
        Assertions.assertTrue(film.isPresent());
        Assertions.assertEquals("AFRICAN EGG", film.get().getTitle());
    }
}
