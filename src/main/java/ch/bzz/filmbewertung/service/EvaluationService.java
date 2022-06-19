package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.Evaluation;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
     * @return all Evaluations from JSON file
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEvaluations() {
        List<Evaluation> evaluationList = DataHandler.getInstance().readAllEvaluations();
        return Response
                .status(200)
                .entity(evaluationList)
                .build();
    }

    /**
     * reads Evaluation from JSON File by its uuid
     *
     * @param evaluationUUID evaluationUUID passed in the url
     * @return Evaluation from JSON file by its uuid
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEvaluation(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String evaluationUUID
    ) {
        int httpStatus = 200;
        Evaluation evaluation = DataHandler.getInstance().readEvaluationByUUID(evaluationUUID);
        if(evaluation == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(evaluation)
                .build();
    }

    /**
     * create Evaluation from the passed values
     *
     * @param evaluation Evaluation which should be inserted
     * @param filmUUID film for which the evaluation has been created
     * @return if Evaluation has successfully been inserted
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertEvaluation(
            @Valid @BeanParam Evaluation evaluation,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID
    ) {
        evaluation.setFilm(filmUUID);
        DataHandler.getInstance().insertEvaluation(evaluation);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates an evaluation if it exists
     *
     * @param evaluation Evaluation with changed data
     * @param filmUUID filmUUID of a film
     * @return returns if the evaluation can be updated or not
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEvaluation(
            @Valid @BeanParam Evaluation evaluation,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("filmUUID") String filmUUID
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
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * delete an evaluation if it exists
     *
     * @param evaluationUUID evaluation to delete
     * @return returns if the evaluation could be deleted or not
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEvaluation(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String evaluationUUID
    ) {
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteEvaluation(evaluationUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
