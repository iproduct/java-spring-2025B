package course.spring.web;

import course.spring.domain.ArticleService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("count")
    public long getArticlesCount() {
        return articleService.getArticlesCount();
    }

    @GetMapping("{id:\\d+}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        var newArticle = articleService.addArticle(article);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment("{id}")
                        .buildAndExpand(newArticle.getId()).toUri()
        ).body(newArticle);
    }

    @PutMapping("{id}")
    public Article updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        if(!id.equals(article.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in request body '%s'.", id, article.getId())
            );
        }
        return articleService.updateArticle(article);
    }

    @DeleteMapping("{id}")
    public Article updateArticle(@PathVariable("id") Long id) {
        return articleService.deleteArticleById(id);
    }


}
