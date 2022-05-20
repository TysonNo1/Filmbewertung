package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Film;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("film")
public class FilmService {

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFilme () {
        List<Film> filmList = DataHandler.getInstance().readALlFilms();
        return Response
                .status(200)
                .entity(filmList)
                .build();
    }
}
