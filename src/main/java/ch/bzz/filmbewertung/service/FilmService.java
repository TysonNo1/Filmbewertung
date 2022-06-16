package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Film;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String filmUUID
    ) {
        int httpStatus = 200;
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if(film == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(film)
                .build();
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

    /**
     * Aktualisiert ein Film
     * @param film Film, welcher aktualisiert werden soll, falls er existiert
     * @return gibt zurück, ob der Film aktualisiert werden konnte oder nicht
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateFilm(
            @Valid @BeanParam Film film
    ) {
        int httpStatus = 200;
        Film alterFilm = DataHandler.getInstance().readFilmByUUID(film.getFilmUUID());
        if(alterFilm != null) {
            alterFilm.setTitel(film.getTitel());
            alterFilm.setGenreByUUID(film.getGenre().getGenreUUID());
            alterFilm.setLaengeInMin(film.getLaengeInMin());
            alterFilm.setIsan(film.getIsan());
            alterFilm.setBewertungen(film.getBewertungen());

            DataHandler.getInstance().updateFilm();
        } else {
            httpStatus = 401;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Löscht einen Film
     * @param filmUUID die FilmUUID von dem gewünschten Film
     * @return gibt zurück, ob der Film gelöscht wurde
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFilm(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String filmUUID
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteFilm(filmUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
