package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Genre;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * GenreService to provide CRUD of Genre
 *
 * @author Erion Malaj
 */
@Path("genre")
public class GenreService {

    /**
     * lists all Genres from JSON File
     *
     * @return all Genres from JSON file
     */
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

    /**
     * reads Genre from JSON File by its uuid
     *
     * @param genreUUID genreUUID passed in the url
     * @return Genre from JSON file by its uuid
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGenre(
            @QueryParam("uuid") String genreUUID
    ) {
        Genre bewertung = DataHandler.getInstance().readGenreByUUID(genreUUID);
        if(bewertung != null) {
            return Response
                    .status(200)
                    .entity(bewertung)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity(bewertung)
                    .build();
        }
    }

    /**
     *
     * @param genre Genre welches erstellt werden soll
     * @return 200 if Genre has successfully been created
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createGenre(
            @Valid @BeanParam Genre genre
    ) {
        DataHandler.getInstance().insertGenre(genre);
        return Response
                .status(200)
                .entity("Successfully added Bewertung")
                .build();
    }
}
