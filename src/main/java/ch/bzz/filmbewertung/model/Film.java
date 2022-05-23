package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

/**
 * Film class which stores the film data, its genre and its bewertungen
 */
public class Film {

    private String filmUUID;
    private Film film;
    private List<Bewertung> bewertungen;
    private String titel;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate veroeffentlichungsdatum;
    private Integer laengeInMin;
    private String isan;
    private Genre genre;

    /**
     * sets genre
     *
     * @param genreUUID value to get the Genre
     */
    @JsonProperty("genre")
    public void setGenreByUUID(String genreUUID) {
        setGenre(DataHandler.getInstance().readGenreByUUID(genreUUID));
    }

    /**
     * sets bewertungUUIDS
     *
     * @param bewertungUUIDS array of bewertungUUIDs
     */
    @JsonProperty("bewertungen")
    public void setBewertungByUUID(List<String> bewertungUUIDS) {
        for (String s : bewertungUUIDS) {
            this.bewertungen.add(DataHandler.getInstance().readBewertungByUUID(s));
        }
    }

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
     * gets bewertungen
     *
     * @return value of bewertungen
     */
    public List<Bewertung> getBewertungen() {
        return bewertungen;
    }

    /**
     * sets bewertungen
     *
     * @param bewertungen the value to set
     */
    public void setBewertungen(List<Bewertung> bewertungen) {
        this.bewertungen = bewertungen;
    }

    /**
     * gets titel
     *
     * @return value of titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * sets titel
     *
     * @param titel the value to set
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * gets veroeffentlichungsdatum
     *
     * @return value of veroeffentlichungsdatum
     */
    public LocalDate getVeroeffentlichungsdatum() {
        return veroeffentlichungsdatum;
    }

    /**
     * sets veroeffentlichungsdatum
     *
     * @param veroeffentlichungsdatum the value to set
     */
    public void setVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum) {
        this.veroeffentlichungsdatum = veroeffentlichungsdatum;
    }

    /**
     * gets laengeInMin
     *
     * @return value of laengeInMin
     */
    public Integer getLaengeInMin() {
        return laengeInMin;
    }

    /**
     * sets laengeInMin
     *
     * @param laengeInMin the value to set
     */
    public void setLaengeInMin(Integer laengeInMin) {
        this.laengeInMin = laengeInMin;
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
    public Genre getGenre() {
        return genre;
    }

    /**
     * sets genre
     *
     * @param genre the value to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
