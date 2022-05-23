package ch.bzz.filmbewertung.data;

import ch.bzz.filmbewertung.model.Bewertung;
import ch.bzz.filmbewertung.model.Film;
import ch.bzz.filmbewertung.model.Genre;
import ch.bzz.filmbewertung.service.Config;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {

    private static DataHandler instance;
    private List<Film> filmList;
    private List<Bewertung> bewertungList;
    private List<Genre> genreList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setBewertungList(new ArrayList<>());
        readBewertungJSON();
        setFilmList(new ArrayList<>());
        readFilmJSON();
        setGenreList(new ArrayList<>());
        readGenreJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * reads all films
     * @return list of films
     */
    public List<Film> readALlFilms() {
        return getFilmList();
    }

    /**
     * reads a film by its uuid
     * @param filmUUID
     * @return the Film (null=not found)
     */
    public Film readFilmByUUID(String filmUUID) {
        Film film = null;
        for(Film entry : getFilmList()) {
            if(entry.getFilmUUID().equals(filmUUID)) {
                film = entry;
            }
        }
        return film;
    }

    /**
     * reads all Bewertungen
     * @return list of bewertungen
     */
    public List<Bewertung> readALlBewertungen() {
        return getBewertungList();
    }

    /**
     * reads a bewertung by its uuid
     * @param bewertungUUID
     * @return the Bewertung (null=not found)
     */
    public Bewertung readBewertungByUUID(String bewertungUUID) {
        Bewertung bewertung = null;
        for(Bewertung entry : getBewertungList()) {
            if(entry.getBewertungUUID().equals(bewertungUUID)) {
                bewertung = entry;
            }
        }
        return bewertung;
    }

    /**
     * reads all Genres
     * @return list of genres
     */
    public List<Genre> readALlGenres() {
        return getGenreList();
    }

    /**
     * reads a genre by its uuid
     * @param genreUUID
     * @return the Genre (null=not found)
     */
    public Genre readGenreByUUID(String genreUUID) {
        Genre genre = null;
        for(Genre entry : getGenreList()) {
            if(entry.getGenreUUID().equals(genreUUID)) {
                genre = entry;
            }
        }
        return genre;
    }

    /**
     * reads the films from the JSON-file
     */
    private void readFilmJSON() {
        try {
            String path = Config.getProperty("filmJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            Film[] filme = objectMapper.readValue(jsonData, Film[].class);
            for (Film film : filme) {
                getFilmList().add(film);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the bewertungen from the JSON-file
     */
    private void readBewertungJSON() {
        try {
            String path = Config.getProperty("bewertungJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            Bewertung[] bewertungen = objectMapper.readValue(jsonData, Bewertung[].class);
            for (Bewertung bewertung : bewertungen) {
                getBewertungList().add(bewertung);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the genres from the JSON-file
     */
    private void readGenreJSON() {
        try {
            String path = Config.getProperty("genreJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            Genre[] genres = objectMapper.readValue(jsonData, Genre[].class);
            for (Genre genre : genres) {
                getGenreList().add(genre);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets filmlist
     *
     * @return value of filmlist
     */
    private List<Film> getFilmList() {
        return filmList;
    }

    /**
     * sets filmlist
     *
     * @param filmList the value to set
     */
    private void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    /**
     * gets bewertungList
     *
     * @return value of bewertungList
     */
    private List<Bewertung> getBewertungList() {
        return bewertungList;
    }

    /**
     * sets filmlist
     *
     * @param bewertungList the value to set
     */
    private void setBewertungList(List<Bewertung> bewertungList) {
        this.bewertungList = bewertungList;
    }

    /**
     * gets genreList
     *
     * @return value of genreList
     */
    private List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * sets genreList
     *
     * @param genreList the value to set
     */
    private void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}
