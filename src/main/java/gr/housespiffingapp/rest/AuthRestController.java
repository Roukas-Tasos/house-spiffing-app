package gr.housespiffingapp.rest;

import gr.housespiffingapp.authentication.AuthenticationService;
import gr.housespiffingapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.housespiffingapp.dto.AuthenticationRequestDTO;
import gr.housespiffingapp.dto.AuthenticationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestController.class);
    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticates a user", description = "Authenticates a user by their username and password")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO>
                authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO)
                    throws AppObjectNotAuthorizedException {

        LOGGER.warn(authenticationRequestDTO.toString());
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(authenticationRequestDTO);
        LOGGER.info("User authenticated");

        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }

}
