package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Film;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * BewertungService to provide CRUD of Bewertung
 *
 * @author Erion Malaj
 */
@Path("bewertung")
public class BewertungService {

    /**
     * lists all Bewertungen from JSON File
     *
     * @return all Bewertungen from JSON file
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBewertungen() {
        List<Bewertung> bewertungList = DataHandler.getInstance().readALlBewertungen();
        return Response
                .status(200)
                .entity(bewertungList)
                .build();
    }

    /**
     * reads Bewertung from JSON File by its uuid
     *
     * @param bewertungUUID bewertungUUID passed in the url
     * @return Bewertung from JSON file by its uuid
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBewertung(
            @QueryParam("uuid") String bewertungUUID
    ) {
        Bewertung bewertung = DataHandler.getInstance().readBewertungByUUID(bewertungUUID);
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
     * create Bewertung from the passed values of form
     * @param bewertung Bewertung welche erstellt werden soll
     *
     * @return 200 if Bewertung has successfully been created
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBewertung(
            @Valid @BeanParam Bewertung bewertung
    ) {
        DataHandler.getInstance().insertBewertung(bewertung);
        return Response
                .status(200)
                .entity("Successfully added Bewertung")
                .build();
    }
}
