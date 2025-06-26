package course.spring.domain.impl;

import course.spring.dao.ArticleRepository;
import course.spring.dao.CategoryRepository;
import course.spring.dao.UserRepository;
import course.spring.domain.ArticleService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NonexistingEntityException(
                        String.format("Article with ID='%s' does not exist.", id)
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticleByTitle(String title) {
        return articleRepository.findByTitleContaining(title);
    }

    @Override
    public Article addArticle(Article article) {
        // manage author and editor
        if (article.getAuthor() == null || article.getAuthor().getId() == null) {
            throw new InvalidEntityDataException(
                    String.format("Author with valid User ID is required.")
            );
        }
        var author = userRepository.findById(article.getAuthor().getId())
                .orElseThrow(() -> new InvalidEntityDataException(
                        String.format("Author with User ID='%s' does not exist.", article.getAuthor().getId())
                ));
        if (article.getEditor() == null || article.getEditor().getId() == null) {
            throw new InvalidEntityDataException(
                    String.format("Author with valid User ID is required.")
            );
        }
        var editor = userRepository.findById(article.getEditor().getId())
                .orElseThrow(() -> new InvalidEntityDataException(
                        String.format("Editor with User ID='%s' does not exist.", article.getAuthor().getId())
                ));
        article.setId(null);
        article.setAuthor(author);
        article.setEditor(editor);

        // manage categories
        if (article.getCategories() == null ||
                article.getCategories().stream().anyMatch(c -> c == null || c.getId() == null)) {
            throw new InvalidEntityDataException(
                    String.format("Invalid category data provided.")
            );
        }
        var categories = article.getCategories().stream()
                .map(category -> categoryRepository
                        .findById(category.getId())
                .orElseThrow(() -> new InvalidEntityDataException(
                        String.format("Category '%s' ID='%s' does not exist.", category.getName(), category.getId())
                ))).collect(Collectors.toSet());
        article.setCategories(categories);
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    @Override
    public Article deleteArticleById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public long getArticlesCount() {
        return 0;
    }
}
