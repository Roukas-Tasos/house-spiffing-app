package gr.housespiffingapp.dto.choreDTO;

import gr.housespiffingapp.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChoreReadOnlyDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDate dueDate;

    private Long categoryId;
}
