package gr.housespiffingapp.service;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryUpdateDTO;
import gr.housespiffingapp.mapper.Mapper;
import gr.housespiffingapp.model.Category;
import gr.housespiffingapp.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    @Override
    public CategoryReadOnlyDTO findById(Long id) throws AppObjectNotFoundException {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Category", "Category with id " + id + " not found"));

        return mapper.mapToCategoryReadOnlyDTO(category);
    }

    @Override
    public CategoryReadOnlyDTO findByName(String name) throws AppObjectNotFoundException {

        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new AppObjectNotFoundException("Category", "Category with name " + name + " not found"));

        return mapper.mapToCategoryReadOnlyDTO(category);
    }

    @Override
    public List<CategoryReadOnlyDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::mapToCategoryReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CategoryReadOnlyDTO save(CategoryInsertDTO categoryInsertDTO) throws AppObjectAlreadyExists {

        if (categoryRepository.findByName(categoryInsertDTO.getName()).isPresent()) {
            throw new AppObjectAlreadyExists("Category", "Category with name " + categoryInsertDTO.getName() + " already exists");
        }

        Category category = mapper.mapToCategoryEntity(categoryInsertDTO);

        Category savedCategory = categoryRepository.save(category);

        return mapper.mapToCategoryReadOnlyDTO(savedCategory);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CategoryReadOnlyDTO update(Long id, CategoryUpdateDTO categoryUpdateDTO)
            throws AppObjectNotFoundException {

        Category existingCategory = categoryRepository.findById(categoryUpdateDTO.getId())
                .orElseThrow(() -> new AppObjectNotFoundException("Category", "Category with id " + id + " not found"));

        existingCategory.setName(categoryUpdateDTO.getName());
        existingCategory.setDescription(categoryUpdateDTO.getDescription());

        return mapper.mapToCategoryReadOnlyDTO(categoryRepository.save(existingCategory));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(Long id) throws AppObjectNotFoundException {

        if (categoryRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Category", "Category with id " + id + " not found");
        }

        categoryRepository.deleteById(id);
    }
}
