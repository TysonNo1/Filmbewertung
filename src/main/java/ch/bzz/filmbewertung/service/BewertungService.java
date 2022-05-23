package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * BewertungService to provide CRUD of Bewertung
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
        return Response
                .status(200)
                .entity(bewertung)
                .build();
    }
}
