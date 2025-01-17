package gr.housespiffingapp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

    private String fullname;
    private String username;
    private String token;
}
