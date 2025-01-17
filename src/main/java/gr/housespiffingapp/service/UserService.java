package gr.housespiffingapp.service;

import gr.housespiffingapp.core.enums.Role;
import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserUpdateDTO;
import gr.housespiffingapp.mapper.Mapper;
import gr.housespiffingapp.model.User;
import gr.housespiffingapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserReadOnlyDTO findById(Long id) throws AppObjectNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id " + id + " not found"));

        return mapper.mapToUserReadOnlyDTO(user);
    }

    @Override
    public List<UserReadOnlyDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::mapToUserReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO)
            throws AppObjectAlreadyExists {

        // Checks if User already exists
        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with username " + userInsertDTO.getUsername() + " already exists");
        }

        User user = mapper.mapToUserEntity(userInsertDTO);

        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return mapper.mapToUserReadOnlyDTO(savedUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO updateUser(Long id, UserUpdateDTO userUpdateDTO)
            throws AppObjectNotFoundException {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("User", "User with id " + id + " not found"));

        if (userUpdateDTO.getFirstname() != null) {
            existingUser.setFirstname(userUpdateDTO.getFirstname());
        }

        if (userUpdateDTO.getLastname() != null) {
            existingUser.setLastname(userUpdateDTO.getLastname());
        }

        if (userUpdateDTO.getUsername() != null) {
            existingUser.setUsername(userUpdateDTO.getUsername());
        }

        if (userUpdateDTO.getPassword() != null) {
            existingUser.setPassword(userUpdateDTO.getPassword());
        }

        if (userUpdateDTO.getRole() != null) {
            existingUser.setRole(userUpdateDTO.getRole());
        }

        User updatedUser = userRepository.save(existingUser);

        return mapper.mapToUserReadOnlyDTO(updatedUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(Long id) throws AppObjectNotFoundException {

        if (!userRepository.existsById(id)) {
            throw new AppObjectNotFoundException("Category", "User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
