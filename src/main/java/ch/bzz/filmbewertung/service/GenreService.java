package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Genre;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("genre")
public class GenreService {

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGenres() {
        List<Genre> genreList = DataHandler.getInstance().readALlGenres();
        return Response
                .status(200)
                .entity(genreList)
                .build();
    }
}
