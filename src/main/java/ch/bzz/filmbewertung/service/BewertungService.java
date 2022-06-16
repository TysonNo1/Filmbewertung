package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Film;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bewertungUUID
    ) {
        int httpStatus = 200;
        Bewertung bewertung = DataHandler.getInstance().readBewertungByUUID(bewertungUUID);
        if(bewertung == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(bewertung)
                .build();
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
            @Valid @BeanParam Bewertung bewertung,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID
    ) {
        bewertung.setFilm(filmUUID);
        DataHandler.getInstance().insertBewertung(bewertung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * aktualisiert eine Bewertung falls sie existiert
     * @param bewertung Bewertung mit aktualiserten Daten
     * @param filmUUID filmUUID es Filmes
     * @return gibt zurück, ob die Bewertung aktualisiert werden kann oder nicht
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBewertung(
            @Valid @BeanParam Bewertung bewertung,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID
    ) {
        int httpStatus = 200;
        Bewertung alteBewertung = DataHandler.getInstance().readBewertungByUUID(bewertung.getBewertungUUID());
        if(alteBewertung != null) {
            alteBewertung.setBegruendung(bewertung.getBegruendung());
            alteBewertung.setLikes(bewertung.getLikes());
            alteBewertung.setSterne(bewertung.getSterne());
            alteBewertung.setFilm(filmUUID);

            DataHandler.getInstance().updateBewertung();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    public Response deleteBewertung(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String bewertungUUID
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteBewertung(bewertungUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
