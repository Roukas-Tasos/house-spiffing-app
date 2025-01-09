package gr.housespiffingapp.dto.categoryDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryUpdateDTO {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(max = 15, message = "Name of category must not exceed 15 characters")
    private String name;

    @NotNull(message = "Description is mandatory")
    @Size(max = 255, message = "Description of category must not exceed 255 characters")
    private String description;
}
