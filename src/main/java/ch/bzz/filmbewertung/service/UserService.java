package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.User;
import ch.bzz.filmbewertung.util.JWToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {

    /**
     * List all users
     * @return Reponse with all users
     */
    @RolesAllowed({"admin"})
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser() {
        List<User> userList = DataHandler.getInstance().readAllUsers();

        return Response
                .status(200)
                .entity(userList)
                .build();
    }

    /**
     * reads a single user by uuid
     * @param userUUID uuid of user
     * @return response of user if existing
     */
    @RolesAllowed({"admin"})
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUser(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("userUUID") String userUUID
    ) {
        User user = null;
        int httpStatus = 200;
        try {
            user = DataHandler.getInstance().readUserByUUID(userUUID);
            if (user == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }

    /**
     * creates a user
     * @param user user to create
     * @return if user created
     */
    @RolesAllowed({"admin", "user"})
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(
            @Valid @BeanParam User user
    ) {
        DataHandler.getInstance().insertUser(user);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates user
     * @param user user to be updated
     * @return response if updated
     */
    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @Valid @BeanParam User user
    ) {
        int httpStatus = 200;
        User oldUser = DataHandler.getInstance().readUserByUUID(user.getUserUUID());
        if (user != null) {
            oldUser.setPassword(user.getPassword());
            oldUser.setUsername(user.getUsername());
            oldUser.setRole(user.getRole());
            DataHandler.getInstance().updateUser();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes an user
     * @param userUUID user to be deleted
     * @return if user got deleted
     */
    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("userUUID") String userUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteUser(userUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * login-service
     *
     * @param username the username
     * @param password the password
     * @return Response
     */
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;
        User user = DataHandler.getInstance().readUser(
                username,
                password
        );

        String token;
        Map<String, Object> claimMap = new HashMap<>();
        int randomWord = 0;
        if (user.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            randomWord = (int) (Math.random() * 4);
            claimMap.put("role", user.getRole());
            claimMap.put("word", user.getWords().get(randomWord));
        }
        token = JWToken.buildToken(user.getRole(), 5, claimMap);

        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomWord + 1 + "",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomWord + 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .cookie(roleCookie)
                .cookie(wordCookie)
                .build();
    }

    /**
     * 2 factor authentication
     * @param authorization authorization
     * @param secret Secret of user
     * @return if word is correct
     */
    @PermitAll
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @HeaderParam("Authorization") String authorization,
            @FormParam("secret") String secret
    ) {
        int httpStatus = 200;
        String token = authorization.substring(7);
        Map<String, String> claims = JWToken.readClaims(token);
        String word = claims.get("word");
        if (word == null || !word.equals(secret)) {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    /**
     * logs a user of its session
     * @return ended session
     */
    @RolesAllowed({"admin", "user"})
    @GET
    @Path("logoff")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoff() {

        NewCookie tokenCookie = new NewCookie(
                "token",
                "",
                "/",
                "",
                "Auth-Token",
                1,
                false);

        return Response
                .status(205)
                .header("Access-Control-Allow-Origin", "*")
                .entity("")
                .cookie(tokenCookie)
                .build();
    }
}
