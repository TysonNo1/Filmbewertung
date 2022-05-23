package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

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
    @JsonIgnore
    private Genre genre;

    @JsonProperty("genre")
    public String getGenreUUID() {
        if(genre != null) {
            return genre.getGenreUUID();
        } else {
            return null;
        }
    }

    public void setGenre(String genreUUID) {
        setGenre(DataHandler.getInstance().readGenreByUUID(genreUUID));
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getFilmUUID() {
        return filmUUID;
    }

    public void setFilmUUID(String filmUUID) {
        this.filmUUID = filmUUID;
    }

    public List<Bewertung> getBewertungen() {
        return bewertungen;
    }

    public void setBewertungen(List<Bewertung> bewertungen) {
        this.bewertungen = bewertungen;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Integer getLaengeInMin() {
        return laengeInMin;
    }

    public void setLaengeInMin(Integer laengeInMin) {
        this.laengeInMin = laengeInMin;
    }

    public LocalDate getVeroeffentlichungsdatum() {
        return veroeffentlichungsdatum;
    }

    public void setVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum) {
        this.veroeffentlichungsdatum = veroeffentlichungsdatum;
    }

    public String getIsan() {
        return isan;
    }

    public void setIsan(String isan) {
        this.isan = isan;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
