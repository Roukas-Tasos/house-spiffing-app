package gr.housespiffingapp.mapper;

import gr.housespiffingapp.core.enums.Role;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.model.Category;
import gr.housespiffingapp.model.Chore;
import gr.housespiffingapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final PasswordEncoder passwordEncoder;

    // User Mappings
    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        User user = new User();

        user.setFirstname(userInsertDTO.getFirstname());
        user.setLastname(userInsertDTO.getLastname());
        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(userInsertDTO.getPassword());
        user.setDateOfBirth(userInsertDTO.getDateOfBirth());
        user.setGender(userInsertDTO.getGender());
        user.setRole(Role.valueOf(userInsertDTO.getRole().name()));
        user.setIsActive(userInsertDTO.getIsActive());
        return user;
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {

        return new UserReadOnlyDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getRole().toString(),
                user.getIsActive());
    }

    // Category Mapper
    public Category mapToCategoryEntity(CategoryInsertDTO categoryInsertDTO) {
        Category category = new Category();
        category.setName(categoryInsertDTO.getName());
        category.setDescription(categoryInsertDTO.getDescription());
        return category;
    }

    public CategoryReadOnlyDTO mapToCategoryReadOnlyDTO(Category category) {

        CategoryReadOnlyDTO dto = new CategoryReadOnlyDTO();

        List<ChoreReadOnlyDTO> choreDTOs = Optional.ofNullable(category.getChores())
                .orElse(Collections.emptySet())
                .stream()
                .map(this::mapToChoreReadOnlyDTO)
                .collect(Collectors.toList());

        dto.setCategoryId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setChores(choreDTOs);
        return dto;
    }

    public ChoreReadOnlyDTO mapToChoreReadOnlyDTO(Chore chore) {

        ChoreReadOnlyDTO dto = new ChoreReadOnlyDTO();

        dto.setId(chore.getId());
        dto.setName(chore.getName());
        dto.setDescription(chore.getDescription());
        dto.setCategoryId(chore.getCategory().getId());
        dto.setDueDate(chore.getDueDate());
        return dto;
    }
}
