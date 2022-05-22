package ch.bzz.filmbewertung.model;

public class Bewertung {

    private String bewertungUUID;
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
