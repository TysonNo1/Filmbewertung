package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Evaluation class which stores the evaluation of a film
 *
 * @author Erion Malaj
 */
public class Evaluation {

    @FormParam("evaluationUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String evaluationUUID;

    @JsonIgnore
    private Film film;

    @FormParam("stars")
    @Min(1)
    @Max(5)
    private Byte stars;

    @FormParam("reason")
    @NotEmpty
    @Size(max = 100)
    private String reason;

    @FormParam("likes")
    @Min(0)
    private Integer likes;

    /**
     * Standard constructor
     */
    public Evaluation() {
    }

    /**
     * Bewertung constructor with data
     * @param film film of evalution
     * @param stars number of starts
     * @param reason reason why it has so many stars
     * @param likes number of likes
     */
    public Evaluation(Film film, Byte stars, String reason, Integer likes) {
        this.film = film;
        this.stars = stars;
        this.reason = reason;
        this.likes = likes;
    }

    /**
     * sets film by its uuid
     *
     * @param filmUUID the value to set
     */
    public void setFilm(String filmUUID) {
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        setFilm(film);
    }

    /**
     * gets evaluationUUID
     *
     * @return value of evaluationUUID
     */
    public String getEvaluationUUID() {
        return evaluationUUID;
    }

    /**
     * sets evaluationUUID
     *
     * @param evaluationUUID the value to set
     */
    public void setEvaluationUUID(String evaluationUUID) {
        this.evaluationUUID = evaluationUUID;
    }

    /**
     * gets film
     *
     * @return value of film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * sets film
     *
     * @param film the value to set
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * gets stars
     *
     * @return value of stars
     */
    public Byte getStars() {
        return stars;
    }

    /**
     * sets stars
     *
     * @param stars the value to set
     */
    public void setStars(Byte stars) {
        this.stars = stars;
    }

    /**
     * gets reason
     *
     * @return value of reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * sets reason
     *
     * @param reason the value to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * gets likes
     *
     * @return value of likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * sets likes
     *
     * @param likes the value to set
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
