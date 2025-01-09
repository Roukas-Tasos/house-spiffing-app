package gr.housespiffingapp.dto.userDTO;

import gr.housespiffingapp.core.enums.GenderType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;

    private String getFullName;

    private String username;

    private LocalDate dateOfBirth;

    private GenderType gender;

    private String role;

}
