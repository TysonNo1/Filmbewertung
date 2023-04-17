package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Film class which stores the film data, its genre and its evaluations
 *
 * @author Erion Malaj
 */
public class Film {

    @FormParam("filmUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String filmUUID;

    @FormParam("evaluations")
    private List<@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}") String> evaluations;

    @FormParam("title")
    @NotEmpty
    @Size(min = 5, max = 40)
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseDate;

    @FormParam("lengthInMin")
    @Min(30)
    @Max(250)
    private Integer lengthInMin;

    @FormParam("isan")
    @NotEmpty
    @Pattern(regexp = "ISAN\\s([0-9A-F]{4}-){4}[0-9A-Z]-([0-9A-F]{4}-){2}[0-9A-Z]")
    private String isan;

    @NotEmpty
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    @FormParam("genre")
    private String genre;

    /**
     * Standard constructor
     */
    public Film() {

    }

    /**
     * Constructor for film
     * @param filmUUID UUID of the film
     * @param evaluations evaluations of the film
     * @param title title of the film
     * @param releaseDate publish date of film
     * @param lengthInMin length in minutes of film
     * @param isan ISAN number of film
     * @param genre Genre of film
     */
    public Film(String filmUUID, List<String> evaluations, String title, LocalDate releaseDate, Integer lengthInMin, String isan, String genre) {
        this.filmUUID = filmUUID;
        this.evaluations = evaluations;
        this.title = title;
        this.releaseDate = releaseDate;
        this.lengthInMin = lengthInMin;
        this.isan = isan;
        this.genre = genre;
    }


    /**
     * sets genre
     *
     * @param genreUUID value to get the Genre
     */
    /*@JsonProperty("genre")
    public void setGenreByUUID(String genreUUID) {
        Genre genre = DataHandler.getInstance().readGenreByUUID(genreUUID);
        if(genre == null) {
            throw new NullPointerException("Not existing Genre");
        }
        setGenre(genre);
    }*/

    /**
     * adds to evaluations by evaluationUUID
     *
     * @param evaluationsUUIDS array of evaluationsUUID
     */
    /*@JsonProperty("evaluationsList")
    public void setEvaluationsByUUID(List<String> evaluationsUUIDS) {
        setEvaluations(new ArrayList<>());
        for (String s : evaluationsUUIDS) {
            Evaluation evaluation = DataHandler.getInstance().readEvaluationByUUID(s);
            if(evaluation == null) {
                throw new NullPointerException("Not existing Evaluation");
            }
            this.evaluations.add(evaluation);
        }
    }/*

    /**
     * gets filmUUID
     *
     * @return value of filmUUID
     */
    public String getFilmUUID() {
        return filmUUID;
    }

    /**
     * sets filmUUID
     *
     * @param filmUUID the value to set
     */
    public void setFilmUUID(String filmUUID) {
        this.filmUUID = filmUUID;
    }

    /**
     * gets evaluations
     *
     * @return value of evaluations
     */
    public List<String> getEvaluations() {
        return evaluations;
    }

    /**
     * sets evaluations
     *
     * @param evaluations the value to set
     */
    public void setEvaluations(List<String> evaluations) {
        this.evaluations = evaluations;
    }

    /**
     * gets title
     *
     * @return value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title
     *
     * @param title the value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets releaseDate
     *
     * @return value of releaseDate
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * sets releaseDate
     *
     * @param releaseDate the value to set
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * gets lengthInMin
     *
     * @return value of lengthInMin
     */
    public Integer getLengthInMin() {
        return lengthInMin;
    }

    /**
     * sets lengthInMin
     *
     * @param lengthInMin the value to set
     */
    public void setLengthInMin(Integer lengthInMin) {
        this.lengthInMin = lengthInMin;
    }

    /**
     * gets isan
     *
     * @return value of isan
     */
    public String getIsan() {
        return isan;
    }

    /**
     * sets isan
     *
     * @param isan the value to set
     */
    public void setIsan(String isan) {
        this.isan = isan;
    }

    /**
     * gets genre
     *
     * @return value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * sets genre
     *
     * @param genre the value to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
