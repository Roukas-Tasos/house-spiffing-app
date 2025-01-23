package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreUpdateDTO;
import gr.housespiffingapp.service.ChoreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChoreRestController {

    private final ChoreService choreService;

    @Operation(summary = "Returns chores", description = "Returns all registered chores from the database")
    @GetMapping("/chores")
    public ResponseEntity<List<ChoreReadOnlyDTO>> getChores() {

        List<ChoreReadOnlyDTO> chores = choreService.findAll();
        return new ResponseEntity<>(chores, HttpStatus.OK);
    }

    @Operation(summary = "Returns chores", description = "Returns all chores belonging to a specific category")
    @GetMapping("/chores/category/{categoryId}")
    public ResponseEntity<List<ChoreReadOnlyDTO>> findAllByCategoryId(@PathVariable Long categoryId) {

        List<ChoreReadOnlyDTO> chores = choreService.findAllByCategoryId(categoryId);
        return new ResponseEntity<>(chores, HttpStatus.OK);
    }

    @Operation(summary = "Returns a chore", description = "Returns a chore by its ID")
    @GetMapping("/chores/{choreId}")
    public ResponseEntity<ChoreReadOnlyDTO> getChore(@PathVariable long choreId)
            throws AppObjectNotFoundException {

        ChoreReadOnlyDTO dto = choreService.findById(choreId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Returns a chore", description = "Returns a chore by its name")
    @GetMapping("/chores/by-name/{name}")
    public ResponseEntity<ChoreReadOnlyDTO> getCategory(@PathVariable String name)
            throws AppObjectNotFoundException {

        ChoreReadOnlyDTO dto = choreService.findByName(name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Inserts a chore", description = "Inserts a new custom chore")
    @PostMapping("/chores/save")
    public ResponseEntity<ChoreReadOnlyDTO> saveChore(@Valid @RequestBody ChoreInsertDTO choreInsertDTO)
            throws AppObjectAlreadyExists, AppObjectNotFoundException {

        ChoreReadOnlyDTO savedChore = choreService.save(choreInsertDTO);
        return new ResponseEntity<>(savedChore, HttpStatus.OK);
    }

    @Operation(summary = "Deletes a chore", description = "Deletes a chore by their ID")
    @DeleteMapping("chores/delete/{id}")
    public String delete(@PathVariable Long id)
            throws AppObjectNotFoundException {

        choreService.delete(id);
        return "User with id " + id + " deleted successfully";
    }

    @Operation(summary = "Updates a chore", description = "Updates an existing chore")
    @PutMapping("/chores/update/{id}")
    public ResponseEntity<ChoreReadOnlyDTO> update(
            @PathVariable Long id,
            @RequestBody ChoreUpdateDTO choreUpdateDTO) throws AppObjectNotFoundException {

        return new ResponseEntity<>(choreService.update(choreUpdateDTO, id), HttpStatus.OK);

    }
}

