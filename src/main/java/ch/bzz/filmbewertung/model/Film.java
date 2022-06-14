package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
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
 * Film class which stores the film data, its genre and its bewertungen
 *
 * @author Erion Malaj
 */
public class Film {

    @FormParam("filmUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String filmUUID;

    @FormParam("bewertungen")
    @NotNull
    private List<Bewertung> bewertungen;

    @FormParam("titel")
    @NotEmpty
    @Size(min = 5, max = 40)
    private String titel;

    @FormParam("veroeffentlichungsdatum")
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate veroeffentlichungsdatum;

    @FormParam("laengeInMin")
    @Min(1)
    private Integer laengeInMin;

    @FormParam("isan")
    @NotEmpty
    @Pattern(regexp = "ISAN\\s([0-9A-F]{4}-){4}[0-9A-Z]-([0-9A-F]{4}-){2}[0-9A-Z]")
    private String isan;

    @FormParam("genre")
    @NotEmpty
    private Genre genre;

    /**
     * Standard constructor
     */
    public Film() {
    }

    /**
     * Constructor for film
     * @param filmUUID UUID of the film
     * @param bewertungen evaluations of the film
     * @param titel title of the film
     * @param veroeffentlichungsdatum publish date of film
     * @param laengeInMin length in minutes of film
     * @param isan ISAN number of film
     * @param genre Genre of film
     */
    public Film(String filmUUID, List<Bewertung> bewertungen, String titel, LocalDate veroeffentlichungsdatum, Integer laengeInMin, String isan, Genre genre) {
        this.filmUUID = filmUUID;
        this.bewertungen = bewertungen;
        this.titel = titel;
        this.veroeffentlichungsdatum = veroeffentlichungsdatum;
        this.laengeInMin = laengeInMin;
        this.isan = isan;
        this.genre = genre;
    }

    /**
     * sets genre
     *
     * @param genreUUID value to get the Genre
     */
    @JsonProperty("genre")
    public void setGenreByUUID(String genreUUID) {
        setGenre(new Genre());
        setGenre(DataHandler.getInstance().readGenreByUUID(genreUUID));
    }

    /**
     * adds to bewertungen by bewertungUUID
     *
     * @param bewertungUUIDS array of bewertungUUIDs
     */
    @JsonProperty("bewertungen")
    public void setBewertungenByUUID(List<String> bewertungUUIDS) {
        setBewertungen(new ArrayList<>());
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
