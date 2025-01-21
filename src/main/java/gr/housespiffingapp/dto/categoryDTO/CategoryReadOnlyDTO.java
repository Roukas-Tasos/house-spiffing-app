package gr.housespiffingapp.dto.categoryDTO;

import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryReadOnlyDTO {

    private Long categoryId;

    private String name;

    private String description;

    private List<ChoreReadOnlyDTO> chores;
}
