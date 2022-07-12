package ch.bzz.filmbewertung.data;

import ch.bzz.filmbewertung.model.Evaluation;
import ch.bzz.filmbewertung.model.Film;
import ch.bzz.filmbewertung.model.Genre;
import ch.bzz.filmbewertung.model.User;
import ch.bzz.filmbewertung.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads, writes, updates and deletes the data in the JSON-files
 *
 * @author Erion Malaj
 */
public class DataHandler {

    private static DataHandler instance;
    private List<Film> filmList;
    private List<Evaluation> evaluationList;
    private List<Genre> genreList;
    private List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setUserList(new ArrayList<>());
        readUserJSON();
        setEvaluationList(new ArrayList<>());
        readEvaluationJSON();
        setGenreList(new ArrayList<>());
        readGenreJSON();
        setFilmList(new ArrayList<>());
        readFilmJSON();
    }

    /**
     * gets the only instance of this class
     * @return DataHandler instance
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
    public List<Film> readAllFilms() {
        return getFilmList();
    }

    /**
     * reads a film by its uuid
     * @param filmUUID uuid of film
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
     * creates a film with passed data
     *
     * @param film Film Object that wants to be inserted
     */
    public void insertFilm(Film film) {
        readAllFilms().add(film);
        writeFilmJSON();
    }

    /**
     * updates film list by re-writing into the list
     */
    public void updateFilm() {
        writeFilmJSON();
    }

    /**
     * deletes the film by its UUID
     * @param filmUUID film to delete
     * @return if the film could be deleted or not
     */
    public boolean deleteFilm(String filmUUID) {
        Film film = readFilmByUUID(filmUUID);
        if(film != null) {
            getFilmList().remove(film);
            writeFilmJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes a film object into JSON File
     */
    public void writeFilmJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("filmJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFilmList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads all Evaluations
     * @return list of evaluations
     */
    public List<Evaluation> readAllEvaluations() {
        return getEvaluationList();
    }

    /**
     * reads a evaluation by its uuid
     * @param evaluationUUID uuid of evaluation
     * @return the Evaluation (null=not found)
     */
    public Evaluation readEvaluationByUUID(String evaluationUUID) {
        Evaluation evaluation = null;
        for(Evaluation entry : getEvaluationList()) {
            if(entry.getEvaluationUUID().equals(evaluationUUID)) {
                evaluation = entry;
            }
        }
        return evaluation;
    }

    /**
     * creates a Evaluation with passed data
     *
     * @param evaluation Object that wants to be inserted
     */
    public void insertEvaluation(Evaluation evaluation) {
        readAllEvaluations().add(evaluation);
        writeEvaluationJSON();
    }

    /**
     * updates the evaluation list by re-writing into the list
     */
    public void updateEvaluation() {
        writeEvaluationJSON();
    }

    /**
     * updates the users list by re-writing into the list
     */
    public void updateUser() {
        writeUserJSON();
    }

    /**
     * delete a evaluation by its uuid
     * @param evaluationUUID evaluation to delete
     * @return if the evaluation could be deleted or not
     */
    public boolean deleteEvaluation(String evaluationUUID) {
        Evaluation evaluation = readEvaluationByUUID(evaluationUUID);
        if(evaluation != null) {
            getEvaluationList().remove(evaluation);
            writeEvaluationJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * delete a user by its uuid
     * @param userUUID user to delete
     * @return if the user could be deleted or not
     */
    public boolean deleteUser(String userUUID) {
        User user = readUserByUUID(userUUID);
        if(user != null) {
            getUserList().remove(user);
            writeUserJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes a Evaluation object into JSON File
     */
    public void writeEvaluationJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("evaluationJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getEvaluationList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
     * @param genreUUID uuid of genre
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
     * reads a user by its uuid
     * @param userUUID uuid of genre
     * @return the User (null=not found)
     */
    public User readUserByUUID(String userUUID) {
        User user = null;
        for(User entry : getUserList()) {
            if(entry.getUserUUID().equals(userUUID)) {
                user = entry;
            }
        }
        return user;
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
     * reads the evaluations from the JSON-file
     */
    private void readEvaluationJSON() {
        try {
            String path = Config.getProperty("evaluationJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            Evaluation[] bewertungen = objectMapper.readValue(jsonData, Evaluation[].class);
            for (Evaluation evaluation : bewertungen) {
                getEvaluationList().add(evaluation);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * creates a Genre with passed data
     *
     * @param genre Object that wants to be inserted
     */
    public void insertGenre(Genre genre) {
        readALlGenres().add(genre);
        writeGenreJSON();
    }

    /**
     * creates a User with passed data
     *
     * @param user Object that wants to be inserted
     */
    public void insertUser(User user) {
        readAllUsers().add(user);
        writeUserJSON();
    }

    /**
     * updates the genre list by re-writing into the list
     */
    public void updateGenre() {
        writeGenreJSON();
    }

    /**
     * deletes a genre by its uuid
     * @param genreUUID genre to delete
     * @return if the genre could be deleted or not
     */
    public boolean deleteGenre(String genreUUID) {
        Genre genre = readGenreByUUID(genreUUID);
        if (genre != null) {
            getGenreList().remove(genre);
            writeGenreJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes a Genre object into JSON File
     */
    public void writeGenreJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("genreJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGenreList());
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
     * reads all Users
     * @return list of genres
     */
    public List<User> readAllUsers() {
        return getUserList();
    }

    /**
     * reads a user by the username/password provided
     *
     * @param username user name
     * @param password user password
     * @return user-object
     */
    public User readUser(String username, String password) {
        User user = new User();
        for (User entry : getUserList()) {
            if (entry.getUsername().equals(username) &&
                    entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * Writes a User object into JSON File
     */
    public void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("userJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGenreList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the users from the JSON-file
     */
    private void readUserJSON() {
        try {
            String path = Config.getProperty("userJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
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
     * gets evaluationsList
     *
     * @return value of evaluationsList
     */
    private List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    /**
     * sets evaluationsList
     *
     * @param evaluationList the value to set
     */
    private void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
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

    /**
     * sets userList
     *
     * @param userList the value to set
     */
    private void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * gets userList
     *
     * @return value of userList
     */
    public List<User> getUserList() {
        return userList;
    }
}
