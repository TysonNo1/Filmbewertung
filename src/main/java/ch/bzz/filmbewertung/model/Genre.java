package ch.bzz.filmbewertung.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * Genre class which stores a type of genre
 *
 * @author Erion Malaj
 */
public class Genre {

    @FormParam("genreUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String genreUUID;

    @FormParam("genre")
    @NotEmpty
    private String genre;

    @FormParam("features")
    @Size.List(@Size(min = 1))
    private List<@NotEmpty String> features;

    /**
     * Standard constructor
     */
    public Genre() {

    }

    /**
     * Genre constructor
     * @param genre Genre of film
     * @param features features of genre
     */
    public Genre(String genre, List<String> features) {
        this.genre = genre;
        this.features = features;
    }

    /**
     * gets genreUUID
     *
     * @return value of genreUUID
     */
    public String getGenreUUID() {
        return genreUUID;
    }

    /**
     * sets genreUUID
     *
     * @param genreUUID the value to set
     */
    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
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

    /**
     * gets features
     *
     * @return value of features
     */
    public List<String> getFeatures() {
        return features;
    }

    /**
     * sets features
     *
     * @param features the value to set
     */
    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
