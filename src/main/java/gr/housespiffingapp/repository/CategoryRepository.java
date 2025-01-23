package gr.housespiffingapp.repository;

import gr.housespiffingapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository
        extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);
}
