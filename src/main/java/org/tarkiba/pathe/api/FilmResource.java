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
}
