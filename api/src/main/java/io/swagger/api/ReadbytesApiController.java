package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.ReadBytes;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-14T05:07:25.653Z[GMT]")
@RestController
public class ReadbytesApiController implements ReadbytesApi {

    private static final Logger log = LoggerFactory.getLogger(ReadbytesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ReadbytesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ReadBytes> listReadBytes(@Parameter(in = ParameterIn.QUERY, description = "tags related to topics of interest news,sports,tech,art" ,schema=@Schema()) @Valid @RequestParam(value = "status", required = false) String status,@Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ReadBytes>(objectMapper.readValue("[ {\n  \"description\" : \"description\",\n  \"id\" : 0,\n  \"tag\" : \"tag\",\n  \"title\" : \"title\",\n  \"url\" : \"url\"\n}, {\n  \"description\" : \"description\",\n  \"id\" : 0,\n  \"tag\" : \"tag\",\n  \"title\" : \"title\",\n  \"url\" : \"url\"\n} ]", ReadBytes.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ReadBytes>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ReadBytes>(HttpStatus.NOT_IMPLEMENTED);
    }

}
