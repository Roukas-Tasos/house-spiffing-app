package gr.housespiffingapp.service;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreUpdateDTO;

import java.util.List;

public interface IChoreService {

    ChoreReadOnlyDTO findById(Long id) throws AppObjectNotFoundException;

    List<ChoreReadOnlyDTO> findAll();

    List<ChoreReadOnlyDTO> findAllByCategoryId(Long categoryId);

    ChoreReadOnlyDTO findByName(String name) throws AppObjectNotFoundException;

    ChoreReadOnlyDTO save(ChoreInsertDTO choreInsertDTO) throws AppObjectAlreadyExists, AppObjectNotFoundException;

    ChoreReadOnlyDTO update(ChoreUpdateDTO choreUpdateDTO, Long id) throws AppObjectNotFoundException;

    ChoreReadOnlyDTO updateDate(ChoreUpdateDTO choreUpdateDTO, Long id) throws AppObjectNotFoundException;

    void delete(Long id) throws AppObjectNotFoundException;
}
