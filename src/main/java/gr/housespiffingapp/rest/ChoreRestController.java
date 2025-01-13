package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreUpdateDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.model.Chore;
import gr.housespiffingapp.model.User;
import gr.housespiffingapp.repository.ChoreRepository;
import gr.housespiffingapp.service.ChoreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChoreRestController {

    private final ChoreService choreService;
    private final ChoreRepository choreRepository;

    @Operation(summary = "Returns chores", description = "Returns all registered chores from the database")
    @GetMapping("/chores")
    public ResponseEntity<List<ChoreReadOnlyDTO>> getChores() {

        List<ChoreReadOnlyDTO> chores = choreService.findAll();
        return new ResponseEntity<>(chores, HttpStatus.OK);
    }

    @Operation(summary = "Returns a chore", description = "Returns a chore by its ID")
    @GetMapping("chores/{choreId}")
    public ResponseEntity<ChoreReadOnlyDTO> getChore(@PathVariable long choreId)
            throws AppObjectNotFoundException {

        ChoreReadOnlyDTO dto = choreService.findById(choreId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Insert a chore", description = "Inserts a new custom chore")
    @PostMapping("/chores/save")
    public ResponseEntity<ChoreReadOnlyDTO> saveChore(@Valid @RequestBody ChoreInsertDTO choreInsertDTO)
            throws AppObjectAlreadyExists, AppObjectNotFoundException {

        ChoreReadOnlyDTO savedChore = choreService.save(choreInsertDTO);
        return new ResponseEntity<>(savedChore, HttpStatus.OK);
    }

    @Operation(summary = "Delete a chore", description = "Delete a chore by their ID")
    @DeleteMapping("chores/{id}")
    public String delete(@PathVariable Long id)
            throws AppObjectNotFoundException {

        choreService.delete(id);
        return "User with id " + id + " deleted successfully";
    }

    @Operation(summary = "Update a chore", description = "Update an existing chore")
    @PutMapping("/chores/update/{id}")
    public ResponseEntity<ChoreReadOnlyDTO> update(
            @PathVariable Long id,
            @RequestBody ChoreUpdateDTO choreUpdateDTO) throws AppObjectNotFoundException {

        return new ResponseEntity<>(choreService.update(choreUpdateDTO, id), HttpStatus.OK);

    }
}

