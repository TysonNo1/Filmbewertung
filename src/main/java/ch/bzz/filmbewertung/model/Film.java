package ch.bzz.filmbewertung.model;

import java.time.LocalDate;
import java.util.List;

public class Film {

    private List<Bewertung> bewertungen;
    private String titel;
    private Integer laengeInMin;
    private LocalDate veroeffentlichungsdatum;
    private String isan;
    private Genre genre;

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
