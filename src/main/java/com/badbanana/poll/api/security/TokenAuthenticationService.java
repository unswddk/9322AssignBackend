package com.badbanana.poll.api.security;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "x-auth-token";

    private final JwtTokenHandler jwtTokenHandler;

    @Autowired
    public TokenAuthenticationService(JwtTokenHandler jwtTokenHandler) {
        this.jwtTokenHandler = jwtTokenHandler;
    }

    public void addJwtTokenToHeader(HttpServletResponse response,
                                    UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();

        String token = jwtTokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);

        response.addHeader("Access-Control-Allow-Origin", "*");
        // add payload
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("token", token);
        Gson gson = new Gson();
        String payload = gson.toJson(userInfo);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(payload);
            System.out.println("new jwt: " + payload);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public Authentication generateAuthenticationFromRequest(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token == null || token.isEmpty()) return null;

        System.out.println("filter auth" + token); // TODO

        return jwtTokenHandler
                .parseUserFromToken(token)
                .map(UserAuthentication::new)
                .orElse(null);
    }
}
