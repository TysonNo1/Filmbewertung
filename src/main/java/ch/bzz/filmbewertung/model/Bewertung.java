package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Bewertung class which stores the evaluation of a film
 *
 * @author Erion Malaj
 */
public class Bewertung {

    private String bewertungUUID;
    @JsonIgnore
    private Film film;
    private Byte sterne;
    private String begruendung;
    private Integer likes;

    /**
     * sets film by its uuid
     *
     * @param filmUUID the value to set
     */
    public void setFilm(String filmUUID) {
        setFilm(new Film());
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        getFilm().setFilmUUID(filmUUID);
        getFilm().setFilm(film);
    }

    /**
     * gets bewertungUUID
     *
     * @return value of bewertungUUID
     */
    public String getBewertungUUID() {
        return bewertungUUID;
    }

    /**
     * sets bewertungUUID
     *
     * @param bewertungUUID the value to set
     */
    public void setBewertungUUID(String bewertungUUID) {
        this.bewertungUUID = bewertungUUID;
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
     * gets sterne
     *
     * @return value of sterne
     */
    public Byte getSterne() {
        return sterne;
    }

    /**
     * sets sterne
     *
     * @param sterne the value to set
     */
    public void setSterne(Byte sterne) {
        this.sterne = sterne;
    }

    /**
     * gets begruendung
     *
     * @return value of begruendung
     */
    public String getBegruendung() {
        return begruendung;
    }

    /**
     * sets begruendung
     *
     * @param begruendung the value to set
     */
    public void setBegruendung(String begruendung) {
        this.begruendung = begruendung;
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
