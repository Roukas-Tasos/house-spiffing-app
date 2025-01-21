package gr.housespiffingapp.service;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.choreDTO.ChoreInsertDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreReadOnlyDTO;
import gr.housespiffingapp.dto.choreDTO.ChoreUpdateDTO;
import gr.housespiffingapp.mapper.Mapper;
import gr.housespiffingapp.model.Category;
import gr.housespiffingapp.model.Chore;
import gr.housespiffingapp.repository.CategoryRepository;
import gr.housespiffingapp.repository.ChoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChoreService implements IChoreService {

    private final ChoreRepository choreRepository;
    private final Mapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ChoreReadOnlyDTO findById(Long id) throws AppObjectNotFoundException {

        Chore chore = choreRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Chore", "Chore with id " + id + " not found"));

        return mapper.mapToChoreReadOnlyDTO(chore);
    }

    @Override
    public List<ChoreReadOnlyDTO> findAll() {
        return choreRepository.findAll()
                .stream()
                .map(mapper::mapToChoreReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChoreReadOnlyDTO> findAllByCategoryId(Long categoryId) {
        return choreRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(mapper::mapToChoreReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ChoreReadOnlyDTO save(ChoreInsertDTO choreInsertDTO) throws AppObjectAlreadyExists, AppObjectNotFoundException {

        if (choreRepository.findByName(choreInsertDTO.getName()).isPresent()) {
            throw new AppObjectAlreadyExists(
                    "Chore",
                    "Chore with name " + choreInsertDTO.getName() + " already exists");
        }

        Category category = categoryRepository
                .findById(choreInsertDTO.getCategoryId())
                .orElseThrow(() -> new AppObjectNotFoundException("Category", "Category not found"));

        Chore chore = new Chore();
        chore.setName(choreInsertDTO.getName());
        chore.setDescription(choreInsertDTO.getDescription());
        chore.setDueDate(choreInsertDTO.getDueDate());
        chore.setCategory(category);

        Chore savedChore = choreRepository.save(chore);

        return mapper.mapToChoreReadOnlyDTO(savedChore);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ChoreReadOnlyDTO update(ChoreUpdateDTO choreUpdateDTO, Long id)
            throws AppObjectNotFoundException {

        Chore existingChore = choreRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Chore", "Chore with id " + id + " not found"));

        return mapper.mapToChoreReadOnlyDTO(choreRepository.save(existingChore));
    }

    @Override
    public ChoreReadOnlyDTO updateDate(ChoreUpdateDTO choreUpdateDTO, Long id)
            throws AppObjectNotFoundException {

        Chore existingChore = choreRepository.findById(id)
                .orElseThrow(() -> new AppObjectNotFoundException("Chore", "Chore with id " + id + " not found"));

        existingChore.setDueDate(choreUpdateDTO.getDueDate());
        Chore updatedChore = choreRepository.save(existingChore);
        return mapper.mapToChoreReadOnlyDTO(updatedChore);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(Long id) throws AppObjectNotFoundException {

        if (choreRepository.existsById(id)) {
            throw new AppObjectNotFoundException("Category", "Chore with id " + id + " not found");
        }
        choreRepository.deleteById(id);
    }
}
