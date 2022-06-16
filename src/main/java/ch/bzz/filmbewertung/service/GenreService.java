package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Genre;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String genreUUID
    ) {
        int httpStatus = 200;
        Genre genre = DataHandler.getInstance().readGenreByUUID(genreUUID);
        if(genre == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(genre)
                .build();
    }

    /**
     * create Genre from the passed values
     *
     * @param genre Genre which wants to be inserted
     * @return if Genre has successfully been inserted
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGenre(
            @Valid @BeanParam Genre genre
    ) {
        DataHandler.getInstance().insertGenre(genre);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a genre if it exists
     *
     * @param genre Genre with changes
     * @return returns if genre could be deleted or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGenre(
            @Valid @BeanParam Genre genre
    ) {
        int httpStatus = 200;
        Genre altesGenre = DataHandler.getInstance().readGenreByUUID(genre.getGenreUUID());
        if(altesGenre != null) {
            altesGenre.setGenre(genre.getGenre());
            altesGenre.setFeatures(genre.getFeatures());

            DataHandler.getInstance().updateGenre();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a genre if it exists
     *
     * @param genreUUID genre that wants to be deleted
     * @return returns if the genre could be deleted or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGenre(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String genreUUID
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteGenre(genreUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
