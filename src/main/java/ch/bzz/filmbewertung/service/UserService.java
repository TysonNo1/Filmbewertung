package ch.bzz.filmbewertung.service;

import ch.bzz.filmbewertung.data.DataHandler;
import ch.bzz.filmbewertung.model.User;
import ch.bzz.filmbewertung.util.JWToken;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {

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
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            randomWord = (int) (Math.random() * 5);
            claimMap.put("role", user.getUserRole());
            claimMap.put("word", user.getWords().get(randomWord));
        }
        token = JWToken.buildToken(user.getUserRole(), 5, claimMap);


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole(),
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
}
