package gr.housespiffingapp.dto.choreDTO;

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
public class ChoreUpdateDTO {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(max = 15, message = "Name of category must not exceed 15 characters")
    private String name;

    @NotNull(message = "Description is mandatory")
    @Size(max = 255, message = "Description of category must not exceed 255 characters")
    private String description;

    @NotNull(message = "Choose a category id for this chore")
    private Category categoryId;

    @NotEmpty(message = "Chore must be assigned to a specific date")
    private LocalDate dueDate;
}
