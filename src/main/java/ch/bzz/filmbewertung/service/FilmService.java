package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Film;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

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
     * @param userRole User Role for cookie
     * @return all Films from JSON file
     */
    @RolesAllowed({"admin", "user"})
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFilms(
            @CookieParam("userRole") String userRole
    ) {
        List<Film> filmList = DataHandler.getInstance().readAllFilms();

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        return Response
                .status(200)
                .entity(filmList)
                .cookie(cookie)
                .build();
    }

    /**
     * reads Film from JSON File by its uuid
     *
     * @param filmUUID filmUUID passed in the url
     * @param userRole User Role for cookie
     * @return Film from JSON file by its uuid
     */
    @RolesAllowed({"admin", "user"})
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFilm(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String filmUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        if(film == null) {
            httpStatus = 410;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        return Response
                .status(httpStatus)
                .entity(film)
                .cookie(cookie)
                .build();
    }

    /**
     * create Film from passed values
     *
     * @param film Film that wants to be inserted
     * @param evaluationUUIDS Evaluations from the film
     * @param genreUUID genre of the film
     * @param releaseDate release date of the film but as string
     * @param userRole User Role for cookie
     * @return if Film has successfully been inserted
     */
    @RolesAllowed({"admin", "user"})
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertFilm(
            @Valid @BeanParam Film film,
            @FormParam("evaluations")
            List<@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}") String>
                    evaluationUUIDS,

            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("genre") String genreUUID,

            @NotEmpty
            @ch.bzz.filmbewertung.constraint.LocalDate
            @FormParam("releaseDate") String releaseDate,

            @CookieParam("userRole") String userRole
    ) {
        film.setGenreByUUID(genreUUID);
        film.setEvaluationsByUUID(evaluationUUIDS);
        film.setReleaseDate(LocalDate.parse(releaseDate));
        DataHandler.getInstance().insertFilm(film);

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }

    /**
     * updates a film
     * @param film Film that wants to be updated if it exists
     * @param evaluationUUIDS evaluations that want to be updated
     * @param genreUUID genre that wants to be updated
     * @param userRole User Role for Cookie
     * @return returns if the film could be updated or not
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateFilm(
            @Valid @BeanParam Film film,
            @NotNull @FormParam("evaluations") List<String> evaluationUUIDS,
            @NotNull @FormParam("genre") String genreUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Film alterFilm = DataHandler.getInstance().readFilmByUUID(film.getFilmUUID());
        if(alterFilm != null) {
            alterFilm.setTitle(film.getTitle());
            alterFilm.setGenreByUUID(genreUUID);
            alterFilm.setLengthInMin(film.getLengthInMin());
            alterFilm.setIsan(film.getIsan());
            alterFilm.setEvaluationsByUUID(evaluationUUIDS);

            DataHandler.getInstance().updateFilm();
        } else {
            httpStatus = 401;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
    }

    /**
     * Deletes a film
     * @param filmUUID Film that wants to be deleted
     * @param userRole User Role for Cookie
     * @return returns if the film could be deleted or not
     */
    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFilm(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String filmUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteFilm(filmUUID)) {
            httpStatus = 410;
        }

        NewCookie cookie = new NewCookie(
                "userRole",
                userRole,
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
    }
}
