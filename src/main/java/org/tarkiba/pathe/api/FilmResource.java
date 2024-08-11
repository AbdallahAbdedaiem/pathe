package org.tarkiba.pathe.api;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.tarkiba.pathe.buisness.IFilmService;

@Path("/")
public class FilmResource {

    @Inject
    IFilmService filmService;

    @GET
    @Path("/hello-world")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World!";
    }

    @GET
    @Path("/hello-world2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        return "Hello World2!";
    }

    @GET
    @Path("/film-title/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilmTitle(@PathParam("filmId") short fildId) {
        return filmService.getFilmTitle(fildId);
    }

    @GET
    @Path("/films/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilmPaged(@PathParam("page") long page, @PathParam("minLength") short minLength) {
        return filmService.pagedFilms(page, minLength);
    }

    @GET
    @Path("/films-with-actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String actors(@PathParam("startsWith") String startsWith, @PathParam("minLength") short minLength) {
        return filmService.actors(startsWith, minLength);
    }

    @GET
    @Path("/films-rental-rate/{minLength}/{rentalRate}")
    @Produces(MediaType.TEXT_PLAIN)
    public String filmsRentalRate(@PathParam("minLength") short minLength, @PathParam("rentalRate") Float rentalRate) {
        return filmService.updateRentalRate(minLength, rentalRate);
    }
}
