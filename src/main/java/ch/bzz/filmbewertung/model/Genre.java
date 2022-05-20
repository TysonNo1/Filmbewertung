package ch.bzz.filmbewertung.model;

import java.util.List;

public class Genre {

    private String genreUUID;
    private String genre;
    private List<String> merkmale;

    public String getGenreUUID() {
        return genreUUID;
    }

    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
    }

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
