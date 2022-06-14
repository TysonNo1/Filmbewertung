package ch.bzz.filmbewertung.model;

import javax.ws.rs.FormParam;
import java.util.List;

/**
 * Genre class which stores a type of genre
 *
 * @author Erion Malaj
 */
public class Genre {

    @FormParam("genreUUID")
    private String genreUUID;

    @FormParam("param")
    private String genre;
    private List<String> merkmale;

    /**
     * Standard constructor
     */
    public Genre() {

    }

    /**
     * Genre constructor
     * @param genre Genre of film
     * @param merkmale features of genre
     */
    public Genre(String genre, List<String> merkmale) {
        this.genre = genre;
        this.merkmale = merkmale;
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
     * gets merkmale
     *
     * @return value of merkmale
     */
    public List<String> getMerkmale() {
        return merkmale;
    }

    /**
     * sets merkmale
     *
     * @param merkmale the value to set
     */
    public void setMerkmale(List<String> merkmale) {
        this.merkmale = merkmale;
    }
}
