package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Evaluation;

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
 * EvaluationService to provide CRUD of Evaluation
 *
 * @author Erion Malaj
 */
@Path("evaluation")
public class EvaluationService {

    /**
     * lists all Evaluations from JSON File
     *
     * @param userRole User Role for Cookie
     * @return all Evaluations from JSON file
     */
    @RolesAllowed({"admin", "user"})
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEvaluations(
            @CookieParam("userRole") String userRole
    ) {
        List<Evaluation> evaluationList = DataHandler.getInstance().readAllEvaluations();

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
                .entity(evaluationList)
                .cookie(cookie)
                .build();
    }

    /**
     * reads Evaluation from JSON File by its uuid
     *
     * @param userRole User Role for Cookie
     * @param evaluationUUID evaluationUUID passed in the url
     * @return Evaluation from JSON file by its uuid
     */
    @RolesAllowed({"admin", "user"})
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEvaluation(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String evaluationUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Evaluation evaluation = DataHandler.getInstance().readEvaluationByUUID(evaluationUUID);
        if(evaluation == null) {
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
                .entity(evaluation)
                .cookie(cookie)
                .build();
    }

    /**
     * create Evaluation from the passed values
     *
     * @param userRole User Role for cookie
     * @param evaluation Evaluation which should be inserted
     * @param filmUUID film for which the evaluation has been created
     * @return if Evaluation has successfully been inserted
     */
    @RolesAllowed({"admin", "user"})
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertEvaluation(
            @Valid @BeanParam Evaluation evaluation,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID,
            @CookieParam("userRole") String userRole
    ) {
        evaluation.setFilm(filmUUID);
        DataHandler.getInstance().insertEvaluation(evaluation);

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
     * updates an evaluation if it exists
     *
     * @param userRole User Role for Cookie
     * @param evaluation Evaluation with changed data
     * @param filmUUID filmUUID of a film
     * @return returns if the evaluation can be updated or not
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEvaluation(
            @Valid @BeanParam Evaluation evaluation,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        Evaluation oldEvaluation = DataHandler.getInstance().readEvaluationByUUID(evaluation.getEvaluationUUID());
        if(oldEvaluation != null) {
            oldEvaluation.setReason(evaluation.getReason());
            oldEvaluation.setLikes(evaluation.getLikes());
            oldEvaluation.setStars(evaluation.getStars());
            oldEvaluation.setFilm(filmUUID);

            DataHandler.getInstance().updateEvaluation();
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
     * delete an evaluation if it exists
     *
     * @param evaluationUUID evaluation to delete
     * @param userRole User Role for cookie
     * @return returns if the evaluation could be deleted or not
     */
    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEvaluation(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String evaluationUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteEvaluation(evaluationUUID)) {
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
