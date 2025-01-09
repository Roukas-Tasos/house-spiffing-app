package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.model.Category;
import gr.housespiffingapp.repository.CategoryRepository;
import gr.housespiffingapp.service.CategoryService;
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
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Operation(summary = "Returns categories", description = "Returns all categories of chores from the database")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryReadOnlyDTO>> getCategories() {

        List<CategoryReadOnlyDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "Returns a category", description = "Returns a category by its ID")
    @GetMapping("categories/{categoryId}")
    public CategoryReadOnlyDTO getCategory(@PathVariable long categoryId)
            throws AppObjectNotFoundException {

        CategoryReadOnlyDTO dto = categoryService.findById(categoryId);

        return dto;
    }

    @Operation(summary = "Insert a category", description = "Inserts a new custom category")
    @PostMapping("/category/save")
    public ResponseEntity<CategoryReadOnlyDTO> save(@Valid @RequestBody CategoryInsertDTO categoryInsertDTO)
            throws AppObjectAlreadyExists {

        CategoryReadOnlyDTO savedCategory = categoryService.save(categoryInsertDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("categories/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) throws AppObjectNotFoundException {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppObjectNotFoundException("Category", "Category with id" + categoryId + " not found"));

        categoryRepository.delete(category);
        return "Category with id " + categoryId + " deleted successfully";
    }
}
