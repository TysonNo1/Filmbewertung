package ch.bzz.filmbewertung.model;

public class Bewertung {

    private Byte sterne;
    private String begruendung;
    private Integer likes;

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
