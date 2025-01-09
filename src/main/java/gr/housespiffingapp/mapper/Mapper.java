package gr.housespiffingapp.mapper;

import gr.housespiffingapp.core.enums.Role;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryUpdateDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreUpdateDTO;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserUpdateDTO;
import gr.housespiffingapp.model.Category;
import gr.housespiffingapp.model.Chore;
import gr.housespiffingapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
@RequiredArgsConstructor
public class Mapper {

    // User Mappings for insert, update, readOnly
    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        User user = new User();
        user.setFirstname(userInsertDTO.getFirstname());
        user.setLastname(userInsertDTO.getLastname());
        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(userInsertDTO.getPassword());
        user.setDateOfBirth(userInsertDTO.getDateOfBirth());
        user.setGender(userInsertDTO.getGender());
        user.setRole(Role.valueOf(userInsertDTO.getRole().name()));
        return user;
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {

        return new UserReadOnlyDTO(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getRole().toString());
    }

    // Category Mapper
    public Category mapToCategoryEntity(CategoryInsertDTO categoryInsertDTO) {
        Category category = new Category();
        category.setName(categoryInsertDTO.getName());
        category.setDescription(categoryInsertDTO.getDescription());
        return category;
    }

    public CategoryReadOnlyDTO mapToCategoryReadOnlyDTO(Category category) {

        return new CategoryReadOnlyDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getChores()
                        .stream()
                        .map(this::mapToChoreReadOnlyDTO)
                        .collect(Collectors.toList()));
    }

    // Chore Mappers
    public Chore mapToChoreEntity(ChoreInsertDTO choreInsertDTO) {
        Chore chore = new Chore();
        chore.setName(choreInsertDTO.getName());
        chore.setDescription(choreInsertDTO.getDescription());
        chore.setCategory(choreInsertDTO.getCategoryId());
        return chore;
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
