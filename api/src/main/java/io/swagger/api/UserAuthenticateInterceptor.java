package io.swagger.api;

import java.util.Optional;
import io.swagger.model.Error;
import io.swagger.model.User;
import io.swagger.model.UserRepository;
import java.util.Collections;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticateInterceptor extends HandlerInterceptorAdapter {
    private static final GsonFactory jsonFactory = new GsonFactory();
    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private UserRepository repository;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String url = request.getRequestURI();
        String authHeader = request.getHeader("Authorization");             

        if (request.getMethod().equalsIgnoreCase("options")) {
            return true;
        }

        switch (url) {
            case "/v1/tokensignin":
                return true;
            default:
                if (url.startsWith("/v1/qrcodes")) {
                    return true;
                }
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            int offset = "Bearer ".length();
            String sessionId = authHeader.substring(offset);

            try {
                Optional<User> user = repository.findBySessionId(sessionId);

                if (user.isPresent()) {
                    return true;
                }

                return false;
            } catch (Exception e) {
               throw new Exception("DB error", e);
            }
        }

        throw new Exception("Forbidden!");
    }
}