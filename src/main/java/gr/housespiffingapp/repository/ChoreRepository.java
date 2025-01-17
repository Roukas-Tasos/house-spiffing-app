package gr.housespiffingapp.repository;

import gr.housespiffingapp.model.Chore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChoreRepository
        extends JpaRepository<Chore, Long>, JpaSpecificationExecutor<Chore> {

    Optional<Chore> findByName(String name);

    List<Chore> findAllByCategoryId(Long categoryId);

    Optional<Chore> findByDueDate(Date dueDate);

}
