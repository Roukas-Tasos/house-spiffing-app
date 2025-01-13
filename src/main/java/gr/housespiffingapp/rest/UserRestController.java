package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.*;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserUpdateDTO;
import gr.housespiffingapp.repository.UserRepository;
import gr.housespiffingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "Returns all users", description = "Returns all registered users from the database")
    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsers() {

        List<UserReadOnlyDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Returns a user", description = "Returns a user by their ID")
    @GetMapping("users/{id}")
    public ResponseEntity<UserReadOnlyDTO> getUser(@PathVariable long id) throws AppObjectNotFoundException {

        UserReadOnlyDTO dto = userService.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Create a user", description = "Creates a new user")
    @PostMapping("/users/save")
    public ResponseEntity<UserReadOnlyDTO> saveUser(
            @Valid @RequestBody UserInsertDTO userInsertDTO, BindingResult bindingResult)
                    throws AppObjectAlreadyExists {

        UserReadOnlyDTO savedUser = userService.saveUser(userInsertDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("users/update{userId}")
    public ResponseEntity<UserReadOnlyDTO> update
            (@Valid @PathVariable Long userId, @RequestBody UserUpdateDTO updateUser, BindingResult  bindingResult)
            throws AppObjectNotFoundException, AppObjectInvalidArgumentException {

        UserReadOnlyDTO updatedUser = userService.update(userId, updateUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID")
    @DeleteMapping("users/{id}")
    public ResponseEntity<UserReadOnlyDTO> deleteUser(@PathVariable Long id)
            throws AppObjectNotFoundException {

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
