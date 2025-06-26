package course.spring.dao;

import course.spring.model.Article;
import course.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContaining(String titlePart);
}
