package gr.housespiffingapp.dto.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    @Size(min = 2, max = 15, message = "Firstname must be between 2 and 15 characters")
    private String firstname;

    @Size(min = 2, max = 15, message = "Lastname must be between 2 and 15 characters")
    private String lastname;

    private String username;

    private String password;

    private String role;
}
