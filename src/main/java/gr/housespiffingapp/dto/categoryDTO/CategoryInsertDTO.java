package gr.housespiffingapp.dto.categoryDTO;

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
public class CategoryInsertDTO {

    @NotEmpty(message = "Category name is required")
    private String name;

    @Size(max = 255, message = "Description of chore must not exceed 255 characters")
    private String description;

}
