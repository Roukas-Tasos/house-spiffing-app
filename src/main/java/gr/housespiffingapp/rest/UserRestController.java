package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.*;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserUpdateDTO;
import gr.housespiffingapp.model.User;
import gr.housespiffingapp.repository.UserRepository;
import gr.housespiffingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final UserRepository userRepository;

    @Operation(summary = "Returns all users", description = "Returns all registered users from the database")
    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsers() {

        List<UserReadOnlyDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Returns a user", description = "Returns a user by their ID")
    @GetMapping("users/{userId}")
    public User getUser(@PathVariable long userId) throws AppObjectNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id" + userId + " not found"));

        return user;
    }

//    @GetMapping("users/{username}")
//    public User getByUsername(@PathVariable String username) throws AppObjectNotFoundException {
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id" + username + " not found"));
//
//        return user;
//    }

    @Operation(summary = "Create a user", description = "Creates a new user")
    @PostMapping("/users/save")
    public ResponseEntity<UserReadOnlyDTO> saveUser(
            @Valid @RequestBody UserInsertDTO userInsertDTO, BindingResult bindingResult)
                    throws AppObjectAlreadyExists {

        UserReadOnlyDTO savedUser = userService.saveUser(userInsertDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

//    @PutMapping("users/update")
//    public User userUpdate(@Valid @RequestBody Long userId, UserUpdateDTO userUpdateDTO, BindingResult  bindingResult)
//            throws AppObjectNotFoundException {
//
//        User user = userService.updateUser(userId, userUpdateDTO);
//        return user;
//    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID")
    @DeleteMapping("users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws AppObjectNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id" + userId + " not found"));

        userRepository.delete(user);
        return "User with id " + userId + " deleted successfully";
    }
}
