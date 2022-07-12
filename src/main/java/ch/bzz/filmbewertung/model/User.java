package ch.bzz.filmbewertung.model;

import java.util.List;

/**
 * User class to authenticate when accessing website
 *
 * @author Erion Malaj
 */
public class User {

    private String userUUID;
    private String username;
    private String password;
    private String role;
    private List<String> words;

    public User() {
        this.username = "guest";
        this.role = "guest";
    }

    /**
     * gets userUUID
     *
     * @return value of userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * sets userUUID
     *
     * @param userUUID the value to set
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets userName
     *
     * @return value of userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets userName
     *
     * @param username the value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password the value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets userRole
     *
     * @return value of userRole
     */
    public String getRole() {
        return role;
    }

    /**
     * sets userRole
     *
     * @param role the value to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * gets words
     *
     * @return value of words
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * sets words
     *
     * @param words the value to set
     */
    public void setWords(List<String> words) {
        this.words = words;
    }
}
