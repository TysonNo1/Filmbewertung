package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Genre;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
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
     * @param userRole User Role for cookie
     * @return all Genres from JSON file
     */
    @RolesAllowed({"admin", "user"})
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGenres(
            @CookieParam("userRole") String userRole
    ) {
        List<Genre> genreList = DataHandler.getInstance().readALlGenres();

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
                .entity(genreList)
                .cookie(cookie)
                .build();
    }

    /**
     * reads Genre from JSON File by its uuid
     *
     * @param userRole User Role for cookie
     * @param genreUUID genreUUID passed in the url
     * @return Genre from JSON file by its uuid
     */
    @RolesAllowed({"admin", "user"})
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGenre(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String genreUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Genre genre = DataHandler.getInstance().readGenreByUUID(genreUUID);
        if(genre == null) {
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
                .entity(genre)
                .cookie(cookie)
                .build();
    }

    /**
     * create Genre from the passed values
     *
     * @param userRole User Role for cookie
     * @param genre Genre which wants to be inserted
     * @return if Genre has successfully been inserted
     */
    @RolesAllowed({"admin", "user"})
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGenre(
            @Valid @BeanParam Genre genre,
            @CookieParam("userRole") String userRole
    ) {
        DataHandler.getInstance().insertGenre(genre);

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
     * updates a genre if it exists
     *
     * @param genre Genre with changes
     * @param userRole User Role for cookie
     * @return returns if genre could be deleted or not
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGenre(
            @Valid @BeanParam Genre genre,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Genre oldGenre = DataHandler.getInstance().readGenreByUUID(genre.getGenreUUID());
        if(oldGenre != null) {
            oldGenre.setGenre(genre.getGenre());
            oldGenre.setFeatures(genre.getFeatures());

            DataHandler.getInstance().updateGenre();
        } else {
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

    /**
     * deletes a genre if it exists
     *
     * @param genreUUID genre that wants to be deleted
     * @param userRole User Role for cookie
     * @return returns if the genre could be deleted or not
     */
    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGenre(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String genreUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteGenre(genreUUID)) {
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
