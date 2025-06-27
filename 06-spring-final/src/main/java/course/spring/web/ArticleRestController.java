package course.spring.web;

import course.spring.domain.ArticleService;
import course.spring.dto.ArticleDetailDto;
import course.spring.dto.mapper.ArticleDtoMapper;
import course.spring.exception.InvalidEntityDataException;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static course.spring.dto.mapper.ArticleDtoMapper.mapArticleToArticleDetailDto;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<ArticleDetailDto> getAllArticles() {
        return articleService.getAllArticles().stream()
                .map(article -> mapArticleToArticleDetailDto(article))
                .collect(Collectors.toList());
    }

    @GetMapping("count")
    public long getArticlesCount() {
        return articleService.getArticlesCount();
    }

    @GetMapping("{id:\\d+}")
    public ArticleDetailDto getArticleById(@PathVariable("id") Long id) {
        return mapArticleToArticleDetailDto(articleService.getArticleById(id));
    }

    @GetMapping(params = {"title", "content"})
    public List<ArticleDetailDto> getArticleByTitleOrContent(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content) {
        return articleService.getArticleByTitleOrContent(title, content).stream()
                .map(article -> mapArticleToArticleDetailDto(article))
                .collect(Collectors.toList());
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
