package gr.housespiffingapp.dto.userDTO;

import gr.housespiffingapp.core.enums.GenderType;
import gr.housespiffingapp.core.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 2, max = 15, message = "Firstname must be between 2 and 15 characters")
    private String firstname;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 2, max = 15, message = "Lastname must be between 2 and 15 characters")
    private String lastname;

    @NotBlank(message = "Email is required as a username")
    @Email(message = "Username must be a valid email address")
//    @Pattern(regexp="^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+/.{2,}$")
    private String username;

//    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Invalid password")
    @Size(min = 6, message = "Password muse be at least 6 characters")
    private String password;

    @NotNull(message = "Date of birth must not me null")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender must not be null")
    private GenderType gender;

    @NotNull(message = "Role must not be null")
    private Role role;

    private Boolean isActive;
}
