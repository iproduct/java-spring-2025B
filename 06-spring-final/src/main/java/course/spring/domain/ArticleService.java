package course.spring.domain;

import course.spring.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(Long id);
    List<Article> getArticleByTitleOrContent(String title, String content);
    Article addArticle(Article article);
    Article updateArticle(Article article);
    Article deleteArticleById(Long id);
    long getArticlesCount();
}
