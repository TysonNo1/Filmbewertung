package ch.bzz.filmbewertung.model;

import java.util.List;

public class Genre {

    private String genre;
    private List<String> merkmale;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getMerkmale() {
        return merkmale;
    }

    public void setMerkmale(List<String> merkmale) {
        this.merkmale = merkmale;
    }
}
