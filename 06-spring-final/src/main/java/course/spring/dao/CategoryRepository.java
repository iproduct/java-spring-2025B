package course.spring.dao;

import course.spring.model.Category;
import course.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContaining(String namePart);
}
