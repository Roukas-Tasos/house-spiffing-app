package gr.housespiffingapp.dto.categoryDTO;

import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.model.Chore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryReadOnlyDTO {

    private Long id;

    private String name;

    private String description;

    private List<ChoreReadOnlyDTO> chores;
}
