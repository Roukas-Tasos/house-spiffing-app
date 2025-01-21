package gr.housespiffingapp.rest;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryUpdateDTO;
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

    @Operation(summary = "Returns categories", description = "Returns all categories of chores from the database")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryReadOnlyDTO>> getCategories() {

        List<CategoryReadOnlyDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "Returns a category", description = "Returns a category by its Id")
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryReadOnlyDTO> getCategory(@PathVariable Long id)
            throws AppObjectNotFoundException {

        CategoryReadOnlyDTO dto = categoryService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Returns a category", description = "Returns a category by its name")
    @GetMapping("/categories/by-name/{name}")
    public ResponseEntity<CategoryReadOnlyDTO> getCategory(@PathVariable String name)
            throws AppObjectNotFoundException {

        CategoryReadOnlyDTO dto = categoryService.findByName(name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Inserts a category", description = "Inserts a new custom category")
    @PostMapping("/categories/save")
    public ResponseEntity<CategoryReadOnlyDTO> save(@Valid @RequestBody CategoryInsertDTO categoryInsertDTO)
            throws AppObjectAlreadyExists {

        CategoryReadOnlyDTO savedCategory = categoryService.save(categoryInsertDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @Operation(summary= "Updates a category", description = "Updates a saved category")
    @PutMapping("categories/update/{id}")
    public ResponseEntity<CategoryReadOnlyDTO> update(@Valid
                                                          @PathVariable Long id,
                                                          @RequestBody CategoryUpdateDTO categoryUpdateDTO) throws AppObjectNotFoundException {

        CategoryReadOnlyDTO updatedCategory = categoryService.update(id, categoryUpdateDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @Operation(summary = "Deletes a category", description = "Deletes a category by its Id")
    @DeleteMapping("categories/{id}")
    public String deleteCategory(@PathVariable Long id) throws AppObjectNotFoundException {

        categoryService.delete(id);
        return "Category with id " + id + " deleted successfully";
    }
}
