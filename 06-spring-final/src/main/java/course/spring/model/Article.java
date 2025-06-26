package course.spring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator="my_seq")
//    @SequenceGenerator(name="my_seq",sequenceName="ARTICLE_SEQ", allocationSize=1)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    private User author;
    @ManyToOne
    private User editor;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime publishDate =  LocalDateTime.now();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags = Collections.emptySet();
    @ManyToMany
    private Set<Category> categories = Collections.emptySet();

    public Article() {
    }

    public Article(Long id) {
        this.id = id;
    }

    public Article(String title, String content, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Article(String title, String content, User author, User editor, LocalDateTime publishDate, Set<String> tags, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.editor = editor;
        this.publishDate = publishDate;
        this.tags = tags;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;

        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("id=").append(getId());
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", content='").append(getContent()).append('\'');
        sb.append(", author=").append(getAuthor());
        sb.append(", editor=").append(getEditor());
        sb.append(", publishDate=").append(getPublishDate());
        sb.append(", tags=").append(getTags());
        sb.append(", categories=").append(getCategories());
        sb.append('}');
        return sb.toString();
    }
}
