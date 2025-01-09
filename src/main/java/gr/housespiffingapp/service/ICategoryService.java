package gr.housespiffingapp.service;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.categoryDTO.CategoryInsertDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryReadOnlyDTO;
import gr.housespiffingapp.dto.categoryDTO.CategoryUpdateDTO;

import java.util.List;

public interface ICategoryService {

    CategoryReadOnlyDTO findById(Long id) throws AppObjectNotFoundException;

    List<CategoryReadOnlyDTO> findAll();

    CategoryReadOnlyDTO save(CategoryInsertDTO categoryInsertDTO) throws AppObjectAlreadyExists;

    CategoryReadOnlyDTO update(Long id, CategoryUpdateDTO categoryUpdateDTO) throws AppObjectNotFoundException;

    void delete(Long id) throws AppObjectNotFoundException;
}
