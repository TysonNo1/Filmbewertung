package ch.bzz.filmbewertung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * User class to authenticate when accessing website
 *
 * @author Erion Malaj
 */
public class User {

    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89ABab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
    private String userUUID;

    @FormParam("username")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String username;

    @FormParam("password")
    @NotEmpty
    @Size(min = 8,max = 30)
    private String password;

    @FormParam("role")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String role;

    @FormParam("words")
    @NotEmpty
    private List<String> words;

    public User() {
        setUsername("guest");
        setRole("guest");
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
