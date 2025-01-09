package gr.housespiffingapp.dto.choreDTO;

import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChoreInsertDTO {

    @NotEmpty(message = "Chore name is required")
    private String name;

    @Size(max = 255, message = "Description of chore must not exceed 255 characters")
    private String description;

    @NotNull(message = "Enter The date to complete this chore")
    private LocalDate dueDate;

    @NotNull(message = "Category ID of chore is required")
    private Category categoryId;
}
