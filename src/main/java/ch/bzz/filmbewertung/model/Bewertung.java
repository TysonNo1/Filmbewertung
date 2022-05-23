package ch.bzz.filmbewertung.model;

import ch.bzz.filmbewertung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bewertung {

    private String bewertungUUID;
    @JsonIgnore
    private Film film;
    private Byte sterne;
    private String begruendung;
    private Integer likes;

    public String getBewertungUUID() {
        return bewertungUUID;
    }

    public void setBewertungUUID(String bewertungUUID) {
        this.bewertungUUID = bewertungUUID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(String filmUUID) {
        setFilm(new Film());
        Film film = DataHandler.getInstance().readFilmByUUID(filmUUID);
        getFilm().setFilmUUID(filmUUID);
        getFilm().setFilm(film);
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Byte getSterne() {
        return sterne;
    }

    public void setSterne(Byte sterne) {
        this.sterne = sterne;
    }

    public String getBegruendung() {
        return begruendung;
    }

    public void setBegruendung(String begruendung) {
        this.begruendung = begruendung;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
