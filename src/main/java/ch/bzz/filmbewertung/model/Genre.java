package ch.bzz.filmbewertung.model;

import java.util.List;

/**
 * Genre class which stores a type of genre
 *
 * @author Erion Malaj
 */
public class Genre {

    private String genreUUID;
    private String genre;
    private List<String> merkmale;

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
