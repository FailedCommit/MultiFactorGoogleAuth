package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.User;
import io.swagger.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.dao.DataIntegrityViolationException;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Collections;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-14T05:07:25.653Z[GMT]")
@RestController
public class TokensigninApiController implements TokensigninApi {
    @Autowired
    private UserRepository repository;
    private static final GsonFactory jsonFactory = new GsonFactory();

    private static final Logger log = LoggerFactory.getLogger(TokensigninApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TokensigninApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> tokenSignIn(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="idToken", required=true)  String idToken) throws GeneralSecurityException {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
		        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
                    .setAudience(Collections.singletonList(System.getenv("OAUTH_CLIENT_ID")))
                    .build();

                // (Receive idTokenString by HTTPS POST)
                GoogleIdToken idTokenString = verifier.verify(idToken.trim());

                if (idTokenString != null) {
                    Payload payload = idTokenString.getPayload();

                    // Print user identifier
                    String userId = payload.getSubject();
                    System.out.println("User ID: " + userId);

                    // Get profile information from payload
                    String email = payload.getEmail();
                    boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                    String familyName = (String) payload.get("family_name");
                    String givenName = (String) payload.get("given_name");

                    try {
                        User user = this.repository.findByEmailId(email);

                        if (user == null) {
                            user = new User();
                            user.setFirstName(givenName);
                            user.setLastName(familyName);
                            user.setEmailId(email);
                            user.set2fa(false);

                            this.repository.save(user);
                        }

                        return new ResponseEntity<User>(user, HttpStatus.OK);
                    } catch (Exception e) {
                        log.info("DB Error", e);
                    }

                    return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    System.out.println("Invalid ID token.");
                    return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
}
