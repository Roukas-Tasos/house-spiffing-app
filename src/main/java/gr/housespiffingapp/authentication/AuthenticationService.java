package gr.housespiffingapp.authentication;

import gr.housespiffingapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.housespiffingapp.dto.AuthenticationRequestDTO;
import gr.housespiffingapp.dto.AuthenticationResponseDTO;
import gr.housespiffingapp.model.User;
import gr.housespiffingapp.repository.UserRepository;
import gr.housespiffingapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new AppObjectNotAuthorizedException("User", "User not authorized"));

        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());

        return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(), token);
    }
}
