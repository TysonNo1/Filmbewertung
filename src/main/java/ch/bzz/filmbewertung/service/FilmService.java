package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Film;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * FilmService to provide CRUD of Film
 *
 * @author Erion Malaj
 */
@Path("film")
public class FilmService {

    /**
     * lists all Films from JSON File
     *
     * @return all Films from JSON file
     */
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

    /**
     * reads Film from JSON File by its uuid
     *
     * @param filmUUID filmUUID passed in the url
     * @return Film from JSON file by its uuid
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFilm(
            @QueryParam("uuid") String filmUUID
    ) {
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if(film != null) {
            return Response
                    .status(200)
                    .entity(film)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity(film)
                    .build();
        }
    }

    /**
     * create Film from passed values from form
     *
     * @param film Film welcher erstellt werden soll
     *
     * @return 200 if Film has successfully been created
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createFilm(
            @Valid @BeanParam Film film
    ) {
        DataHandler.getInstance().insertFilm(film);
        return Response
                .status(200)
                .entity("Successfully added Film")
                .build();
    }
}
